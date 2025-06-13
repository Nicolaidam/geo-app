package dk.example.geoapp.domain.geoapiclient.model

import dk.example.geoapp.domain.geoapiclient.dto.GeoItemTypeDto


data class GeoItemType(
    val code: GeoItemCode,
    val name: String,
)

/**
 * Maps a *network/disk* DTO into a pure domain model.
 * Domain objects remain immutable; we enrich the freshly‑created [GeoItemType] with a
 * calculated `distanceInKm` (if a `currentLocation` is supplied) via `copy`.
 *
 * Keeping this mapping in the data layer honours clean‑architecture boundaries:
 *   • DTO → Domain lives in the mapper,
 *   • calculation logic stays inside the *Domain* entity (see `GeoItem.distanceInKmTo`).
 */
fun GeoItemTypeDto.toModel(): GeoItemType {
    val type = GeoItemCode.entries
        .firstOrNull { it.code == this.id }
        ?: throw IllegalArgumentException("Unknown GeoItemType id \"${this.id}\"")

    return GeoItemType(
        code = type,
        name = this.name
    )
}

enum class GeoItemCode(val code: String) {
    BÆNK("1371"),
    TELTPLADS("3031"),
    BADESTED("1051"),
    UDSIGT("2022"),
    TØRVEJR("1132"),
    TOILET("1012"),
    PARKERING("1351"),
    BÅLPLADS("1022"),
    PICNIC("1201"),
    LEGEPLADS("1031"),
    HOLDEPLADS("1291"),
    SHELTER("3012");
}
