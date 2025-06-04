package dk.example.geoapp


import dk.example.geoapp.config.configureCallLogging
import dk.example.geoapp.config.configureCors
import dk.example.geoapp.config.configureSerialization
import dk.example.geoapp.geoapi.CachedGeoApi
import dk.example.geoapp.geoapi.GeoApiImpl
import dk.example.geoapp.model.GeoItemDto
import dk.example.geoapp.routing.configureAppController
import dk.example.geoapp.routing.configureRouting
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import java.io.File
import kotlinx.serialization.encodeToString
import org.slf4j.LoggerFactory

//val geoApi = CachedGeoApi(
//    wrapped = GeoApiImpl(
//        client = HttpClient {
//            install(ContentNegotiation) {
//                json(Json {
//                    ignoreUnknownKeys = true
//                })
//            }
//            install(HttpTimeout) {
//                socketTimeoutMillis = 999999_000
//                connectTimeoutMillis = 999999_000
//                requestTimeoutMillis = 999999_000
//            }
//        }
//    )
//)


val geoApi = GeoApiImpl(
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
)
private val logger = LoggerFactory.getLogger("Main")
suspend fun main(args: Array<String>) {
    logger.info("Starting geo-app")

    logger.info("Fetching geo data")
    val data: Result<List<GeoItemDto>> = geoApi.fetchData(types = emptyList())
    data.fold(
        onSuccess = { list ->
            logger.info("Fetched {} geo items; caching to disk", list.size)
            val jsonString = Json { prettyPrint = true }.encodeToString(list)
            val cacheDir = File("cache")
            if (!cacheDir.exists()) {
                cacheDir.mkdirs()
            }
            File(cacheDir, "response_cache.json").writeText(jsonString)
        },
        onFailure = { throwable ->
            logger.error("Failed to fetch geo data", throwable)
        }
    )

    logger.info("Launching web server")
    EngineMain.main(args = args)
}

fun Application.module() {
    configureCallLogging()
    configureCors()
    configureAppController(geoApi = geoApi)
    configureRouting()
    configureSerialization()
}
