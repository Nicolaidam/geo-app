package dk.example.geoapp.infrastructure.geofaapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoInfoDto(
    val success: Boolean,
    val features: List<PhotoFeature>
)

@Serializable
data class PhotoFeature(
    val properties: PhotoProperties
)

@Serializable
data class PhotoProperties(
    @SerialName("foto_navn")
    val fotoNavn: String? = null,
)
