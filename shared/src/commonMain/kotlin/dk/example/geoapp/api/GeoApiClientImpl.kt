package dk.example.geoapp.api

import dk.example.geoapp.httpClientEngine
import dk.example.geoapp.model.Response
import dk.example.geoapp.model.GeoItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.CancellationException
import kotlinx.serialization.json.Json

class GeoApiClientImpl(
    rawBaseUrl: String,
) : GeoApiClient {
    private val baseUrl = rawBaseUrl.trimEnd('/')

    private val client = HttpClient(httpClientEngine()) {
        expectSuccess = true                       // auto-throws on !2xx
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        install(Logging) { logger = Logger.SIMPLE; level = LogLevel.INFO }
        // quick, sane defaults
        install(HttpTimeout) { requestTimeoutMillis = 15_000 }
        install(HttpRequestRetry) { retryOnServerErrors(maxRetries = 2) }

        defaultRequest { url { takeFrom(baseUrl) } }
    }

    override suspend fun getGeoItems(): Response<List<GeoItemDto>> = try {
        val geoItems = client.get("/list/1351").body<List<GeoItemDto>>()
        Response.Success(geoItems)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Response.Error(e)
    }
}
