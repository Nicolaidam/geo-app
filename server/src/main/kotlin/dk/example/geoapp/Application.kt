package dk.example.geoapp

import dk.example.geoapp.application.usecases.GetGeoItems
import dk.example.geoapp.application.usecases.GetGeoTypes
import dk.example.geoapp.presentation.configureAppController
import dk.example.geoapp.infrastructure.ktor.configureCallLogging
import dk.example.geoapp.infrastructure.ktor.configureCors
import dk.example.geoapp.infrastructure.ktor.configureSerialization
import dk.example.geoapp.infrastructure.geofaapi.GeofaApiClientImpl
import dk.example.geoapp.infrastructure.filesystem.GeoItemsFilesystemCache
import dk.example.geoapp.infrastructure.scheduler.startDailyRefreshJob
import dk.example.geoapp.application.usecases.RefreshGeoItems
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpTimeoutConfig.Companion.INFINITE_TIMEOUT_MS
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.*
import java.io.File
import kotlinx.serialization.json.Json
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    EngineMain.main(args = args)
}

fun Application.module() {

    val geoItemsCache  = GeoItemsFilesystemCache(
        cacheDir = File("cache"),
        cacheFileName = "response_cache.json"
    )

    val refreshGeoItems   = RefreshGeoItems(
        geofaApiClient = GeofaApiClientImpl(
            HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                    })
                }
                install(HttpTimeout) {
                    socketTimeoutMillis = INFINITE_TIMEOUT_MS
                    connectTimeoutMillis = INFINITE_TIMEOUT_MS
                    requestTimeoutMillis = INFINITE_TIMEOUT_MS
                }
                install(HttpRequestRetry) {  retryOnServerErrors(maxRetries = 3) }
            }
        ),
        cache = geoItemsCache
    )

    configureCallLogging()
    configureCors()
    configureSerialization()
    configureAppController(
        getGeoItems = GetGeoItems(geoItemsCache),
        getGeoTypes = GetGeoTypes(geoItemsCache)
    )

    // Start daily refresh job
    startDailyRefreshJob { refreshGeoItems() }

    // Kick-off an *immediate* geo items refresh at start-up
    launch { refreshGeoItems() }
}

