package dk.example.geoapp.model

import dk.example.geoapp.Location
import kotlinx.serialization.Serializable
import kotlin.math.sqrt
import kotlin.math.roundToInt

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
    // Create the domain object first
    val domainItem = GeoItem(
        id = id,
        name = name ?: "Toilet",
        description = description,
        latitude = latitude,
        longitude = longitude,
        paymentRequired = paymentRequired,
        photoUrls = photoUrls
    )

    // Calculate distance lazily—only when we have a reference point
    val distance = currentLocation?.let { domainItem.distanceInKmTo(it) }

    // Return an immutable copy containing the calculated distance, if any
    return domainItem.copy(distanceInKm = distance)
}

@Serializable
data class GeoItemDto(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val latitude: Double,
    val longitude: Double,
    val paymentRequired: Boolean? = null,
    val photoUrls: List<String>,
)

data class GeoItem(
    val id: String,
    val name: String,
    val description: String?,
    val latitude: Double,
    val longitude: Double,
    val paymentRequired: Boolean? = null,
    val photoUrls: List<String>,
    val distanceInKm: Double? = null,
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
