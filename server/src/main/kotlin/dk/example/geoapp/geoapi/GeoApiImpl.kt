package dk.example.geoapp.geoapi

import dk.example.geoapp.GeoApiResponse
import dk.example.geoapp.model.GeoItemDto
import dk.example.geoapp.toDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers

class GeoApiImpl(private val client: HttpClient): GeoApi {
    override suspend fun fetchData(types: List<String>): Result<List<GeoItemDto>> {
        return try {
            /* 1️⃣  Build WHERE clause only when we have at least one type */
            val where = if (types.isEmpty()) {
                ""                                             // → fetch *all* types
            } else {
                types.joinToString(
                    prefix  = " WHERE facil_ty_k IN (",
                    postfix = ")",
                    separator = ","
                ) { "'$it'" }                                  // '1012','3012',...
            }

            /* 2️⃣  Assemble and URL-encode the SQL */
            val rawSql = "SELECT * FROM fkg.t_5800_fac_pkt$where"
            val url = "https://geofa.geodanmark.dk/api/v2/sql/fkg?q=" +
                    URLEncoder.encode(rawSql, StandardCharsets.UTF_8) +
                    "&srs=4326"

            val response: HttpResponse = client.get(url)
            if (response.status == HttpStatusCode.OK) {
                val dto = response.body<GeoApiResponse>().features.map {
                    it.properties.toDto(geometry = it.geometry)
                }
                // Concurrently fetch photo names for each item
                val enrichedDtos = coroutineScope {
                    dto.map { item ->
                        async(Dispatchers.IO) {
                            println("Fetching photos for item: ${item.id}")
                            val photoUrl = "https://geofa.geodanmark.dk/api/v2/sql/fkg?q=SELECT%20foto_navn,oprettet%20FROM%20fkg.t_7900_fotoforbindelse%20WHERE%20foto_objek=%27${item.id}%27"
                            val photoResponse = client.get(photoUrl)
                            if (photoResponse.status == HttpStatusCode.OK) {
                                val photoDto = photoResponse.body<PhotoInfoResponse>()
                                val photoFilenames = photoDto.features.mapNotNull { feature ->
                                    feature.properties.fotoNavn
                                }
                                val photoUrls = photoFilenames.map { filename ->
                                    "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/$filename"
                                }
                                item.copy(photoUrls = photoUrls)
                            } else {
                                item
                            }
                        }
                    }.awaitAll()
                }
                Result.success(enrichedDtos)
            }
            else {
                Result.failure(Exception("Http error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
