package dk.example.geoapp.infrastructure.geofaapi

import dk.example.geoapp.domain.GeofaApiClient
import dk.example.geoapp.infrastructure.geofaapi.dto.GeofaApiResponseDto
import dk.example.geoapp.infrastructure.geofaapi.dto.PhotoInfoDto
import dk.example.geoapp.infrastructure.geofaapi.dto.toDto
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GeofaApiClientImpl(private val httpClient: HttpClient): GeofaApiClient {
    override suspend fun fetchGeoItems(types: List<String>): Result<List<GeoItemDto>> {
        return try {

            val whereStatement = if (types.isEmpty()) {
                ""
            } else {
                types.joinToString(
                    prefix  = " WHERE facil_ty_k IN (",
                    postfix = ")",
                    separator = ","
                ) { "'$it'" }
            }

            val rawSql = "SELECT * FROM fkg.t_5800_fac_pkt$whereStatement"
            val url = "https://geofa.geodanmark.dk/api/v2/sql/fkg?q=" +
                    URLEncoder.encode(rawSql, StandardCharsets.UTF_8) +
                    "&srs=4326"

            val response: HttpResponse = httpClient.get(url)
            if (response.status == HttpStatusCode.Companion.OK) {
                val dto = response.body<GeofaApiResponseDto>().features
                // Concurrently fetch photo names for each item
                val enrichedDtos = coroutineScope {
                    dto.map { item ->
                        async(Dispatchers.IO) {
                            println("Fetching photos for item: ${item.properties.objektId}")
                            val photoUrl = "https://geofa.geodanmark.dk/api/v2/sql/fkg?q=" +
                                URLEncoder.encode(
                                    "SELECT foto_navn, oprettet FROM fkg.t_7900_fotoforbindelse WHERE foto_objek='${item.properties.objektId}'",
                                    StandardCharsets.UTF_8
                                )
                            val photoResponse = httpClient.get(photoUrl)
                            if (photoResponse.status == HttpStatusCode.Companion.OK) {
                                val photoDto = photoResponse.body<PhotoInfoDto>()
                                val photoFilenames = photoDto.features.mapNotNull { feature ->
                                    feature.properties.fotoNavn
                                }
                                val photoUrls = photoFilenames.map { filename ->
                                    "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/$filename"
                                }
                                item.toDto(photoUrls = photoUrls)
                            } else {
                                item.toDto(photoUrls = emptyList())
                            }
                        }
                    }.awaitAll()
                }
                Result.success(enrichedDtos.filterNotNull())
            }
            else {
                Result.failure(Exception("Http error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
