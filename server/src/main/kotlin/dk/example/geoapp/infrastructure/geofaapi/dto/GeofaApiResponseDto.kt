package dk.example.geoapp.infrastructure.geofaapi.dto

import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemTypeDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeofaApiResponseDto(
    val success: Boolean,
    val features: List<Feature>
)

@Serializable
data class Feature(
    val type: String,
    val geometry: Geometry,
    val properties: Properties
)

@Serializable
data class Geometry(
    val type: String,
    val coordinates: List<List<Double>>
)

@Serializable
data class Properties(
    @SerialName("objekt_id")
    val objektId: String,
    @SerialName("facil_ty")
    val facilTy: String? = null,
    @SerialName("facil_ty_k")
    val facilTyK: Int? ? = null,
    val navn: String? = null,
    val beskrivels: String? = null,
    val betaling: String? = null,
)

fun Feature.toDto(photoUrls: List<String>): GeoItemDto? {
    if (properties.facilTy == null || properties.facilTyK == null) {
        return null
    }
    return GeoItemDto(
        id = properties.objektId,
        name = properties.navn,
        description = properties.beskrivels,
        latitude = geometry.coordinates[0][1],
        longitude = geometry.coordinates[0][0],
        paymentRequired = when {
            properties.betaling.equals("ja", ignoreCase = true) -> true
            properties.betaling.equals("nej", ignoreCase = true) -> false
            else -> null
        },
        photoUrls = photoUrls,
        geoItemType = GeoItemTypeDto(
            id = properties.facilTyK.toString(),
            name = properties.facilTy,
        )
    )
}
