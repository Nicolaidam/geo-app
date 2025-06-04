package dk.example.geoapp

import dk.example.geoapp.model.GeoItemDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoApiResponse(
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
    val navn: String? = null,
    val beskrivels: String? = null,
    val betaling: String? = null,
)

fun Properties.toDto(geometry: Geometry): GeoItemDto {
    return GeoItemDto(
        id = objektId,
        name = navn,
        description = beskrivels,
        latitude = geometry.coordinates[0][1],
        longitude = geometry.coordinates[0][0],
        paymentRequired = when {
            betaling.equals("ja", ignoreCase = true) -> true
            betaling.equals("nej", ignoreCase = true) -> false
            else -> null
        },
        photoUrls = emptyList(),
    )
}
