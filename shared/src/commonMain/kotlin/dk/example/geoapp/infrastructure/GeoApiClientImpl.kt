package dk.example.geoapp.infrastructure

import dk.example.geoapp.LocationService
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemTypeDto
import dk.example.geoapp.domain.geoapiclient.dto.GetItemsRequestInput
import dk.example.geoapp.domain.geoapiclient.dto.TypesRequestInput
import dk.example.geoapp.domain.geoapiclient.GeoApiClient
import dk.example.geoapp.domain.geoapiclient.model.GeoItem
import dk.example.geoapp.domain.geoapiclient.model.GeoItemType
import dk.example.geoapp.domain.geoapiclient.model.GeoItemCode
import dk.example.geoapp.domain.geoapiclient.model.Path
import dk.example.geoapp.domain.geoapiclient.model.toModel
import dk.example.geoapp.httpClientEngine
import dk.example.geoapp.locationService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GeoApiClientImpl(
    rawBaseUrl: String,
) : GeoApiClient {
    private val baseUrl = rawBaseUrl.trimEnd('/')
    val locationService: LocationService = locationService()
    val httpClientEngine: HttpClientEngine = httpClientEngine()

    private val client = HttpClient(httpClientEngine) {
        expectSuccess = true
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        install(Logging) {
            logger = Logger.Companion.SIMPLE; level = LogLevel.INFO
        }
        install(HttpTimeout) { requestTimeoutMillis = 15_000 }
        install(HttpRequestRetry) { retryOnServerErrors(maxRetries = 3) }
        defaultRequest { url { takeFrom(baseUrl) } }
    }

    override suspend fun fetchGeoItems(types: List<GeoItemCode>): Result<List<GeoItem>> {
        return Result.success(
            client.post(Path.geoItems) {
                setBody(GetItemsRequestInput(types = types.map { it.code }))
                contentType(ContentType.Application.Json)
            }.body<List<GeoItemDto>>()
                .map {
                    it.toModel(locationService.getLocation())
                }
        )
    }

    override suspend fun fetchGeoTypes(minimumSize: Int?): Result<List<GeoItemType>> {
        return Result.success(
            client.post(Path.geoTypes) {
                setBody(TypesRequestInput(minimumSize = minimumSize))
                contentType(ContentType.Application.Json)
            }.body<List<GeoItemTypeDto>>()
                .map {
                    it.toModel()
                }
        )
    }
}
