package dk.example.geoapp.domain.geoapiclient.model

import dk.example.geoapp.Location
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import kotlin.math.roundToInt
import kotlin.math.sqrt

data class GeoItemId(val id: String)

data class GeoItem(
    val id: GeoItemId,
    val name: String,
    val description: String?,
    val latitude: Double,
    val longitude: Double,
    val paymentRequired: Boolean? = null,
    val photoUrls: List<String>,
    val distanceInKm: Double? = null,
    val type: GeoItemType,
) {
    /**
     * Returns distance in kilometres from *this* item to the given point.
     * Uses a simple Pythagorean approximation—accurate enough for intra‑city use.
     */
    private fun distanceInKmTo(
        targetLat: Double,
        targetLon: Double
    ): Double {
        val dLat = targetLat - latitude
        val dLon = targetLon - longitude
        return sqrt(dLat * dLat + dLon * dLon) * 111.0
    }

    /**
     * Distance between two items.
     */
    fun distanceInKmTo(other: Location?): Double? {
        if (other == null) return null
        val raw = distanceInKmTo(other.latitude, other.longitude)
        return ((raw * 10).roundToInt()) / 10.0
    }
}

/**
 * Maps a *network/disk* DTO into a pure domain model.
 * Domain objects remain immutable; we enrich the freshly‑created [GeoItem] with a
 * calculated `distanceInKm` (if a `currentLocation` is supplied) via `copy`.
 *
 * Keeping this mapping in the data layer honours clean‑architecture boundaries:
 *   • DTO → Domain lives in the mapper,
 *   • calculation logic stays inside the *Domain* entity (see `GeoItem.distanceInKmTo`).
 */
fun GeoItemDto.toModel(currentLocation: Location?): GeoItem {
    val domainItem = GeoItem(
        id = GeoItemId(this.id),
        name = name ?: "Toilet",
        description = description,
        latitude = latitude,
        longitude = longitude,
        paymentRequired = paymentRequired,
        photoUrls = photoUrls,
        type = this.geoItemType.toModel()
    )
    val distance = currentLocation?.let { domainItem.distanceInKmTo(it) }
    return domainItem.copy(distanceInKm = distance)
}
