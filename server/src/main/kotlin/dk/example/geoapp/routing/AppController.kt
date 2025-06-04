package dk.example.geoapp.routing

import dk.example.geoapp.geoapi.GeoApi
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
private data class TypesRequest(val types: List<String>)

fun Application.configureAppController(geoApi: GeoApi) {
    routing {
        post("/list") {
            val payload = runCatching { call.receive<TypesRequest>() }.getOrNull()
            if (payload == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing JSON body")
                return@post
            }
            val types   = payload.types

            // 2️⃣  Ask the GeoApi
            val result  = geoApi.fetchData(types)
            environment.log.info("GeoApi result for $types → $result")

            // 3️⃣  Respond
            result.fold(
                onSuccess = { call.respond(it) },
                onFailure = {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Failed to fetch $types: ${it.message}"
                    )
                }
            )
        }
    }
}
