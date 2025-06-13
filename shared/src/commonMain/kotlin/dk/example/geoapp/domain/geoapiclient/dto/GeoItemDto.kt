package dk.example.geoapp.domain.geoapiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class GeoItemDto(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val latitude: Double,
    val longitude: Double,
    val paymentRequired: Boolean? = null,
    val photoUrls: List<String>,
    val geoItemType: GeoItemTypeDto,
)

@Serializable
data class GeoItemTypeDto(
    val id: String,
    val name: String,
)


