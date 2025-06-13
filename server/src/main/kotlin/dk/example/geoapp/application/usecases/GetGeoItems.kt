package dk.example.geoapp.application.usecases

import dk.example.geoapp.domain.GeoItemCache

class GetGeoItems(private val cache: GeoItemCache) {
    operator fun invoke(types: List<String>) = cache.load(types)
}
