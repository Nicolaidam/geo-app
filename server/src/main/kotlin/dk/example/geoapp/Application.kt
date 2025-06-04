package dk.example.geoapp


import dk.example.geoapp.config.configureCallLogging
import dk.example.geoapp.config.configureCors
import dk.example.geoapp.config.configureSerialization
import dk.example.geoapp.geoapi.CachedGeoApi
import dk.example.geoapp.geoapi.GeoApiImpl
import dk.example.geoapp.routing.configureAppController
import dk.example.geoapp.routing.configureRouting
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json

val geoApi = CachedGeoApi(
    wrapped = GeoApiImpl(
        client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 999999_000
                connectTimeoutMillis = 999999_000
                requestTimeoutMillis = 999999_000
            }
        }
    ))

suspend fun main(args: Array<String>) {
    EngineMain.main(args = args)
    // Do call here to fetch Toilet locations
    geoApi.fetchData(types = emptyList())
}

fun Application.module() {
    configureCallLogging()
    configureCors()
    configureAppController(geoApi = geoApi)
    configureRouting()
    configureSerialization()
}

