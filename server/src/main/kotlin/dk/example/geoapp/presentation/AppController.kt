package dk.example.geoapp.presentation

import dk.example.geoapp.application.usecases.GetGeoItems
import dk.example.geoapp.application.usecases.GetGeoTypes
import dk.example.geoapp.domain.geoapiclient.dto.GetItemsRequestInput
import dk.example.geoapp.domain.geoapiclient.dto.TypesRequestInput
import dk.example.geoapp.domain.geoapiclient.model.Path
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureAppController(getGeoItems: GetGeoItems, getGeoTypes: GetGeoTypes) {
    routing {
        post(Path.geoItems)  {
            val payload = call.receiveNullable<GetItemsRequestInput>() ?: return@post call.respond(
                HttpStatusCode.BadRequest,
                "Body must be JSON like  { \"types\": [\"1012\"] }"
            )
            val geoItems = getGeoItems(payload.types)
            geoItems.fold(
                onSuccess = { geoItems ->
                    call.respond(
                        HttpStatusCode.OK,
                        geoItems
                    )
                },
                onFailure = {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        it.message ?: "Something went wrong")
                }
            )
        }
        post(Path.geoTypes) {
            val payload = call.receiveNullable<TypesRequestInput>() ?: return@post call.respond(
                HttpStatusCode.BadRequest,
                "Body must be JSON like  { \"minimumSize\": 500 }"
            )
            call.respond( getGeoTypes(payload.minimumSize).getOrThrow() )
        }
    }
}
