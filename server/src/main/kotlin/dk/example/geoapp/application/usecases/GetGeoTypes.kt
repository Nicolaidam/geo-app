package dk.example.geoapp.application.usecases

import dk.example.geoapp.domain.GeoItemCache

class GetGeoTypes(private val cache: GeoItemCache) {
    operator fun invoke(min: Int?) = cache.loadTypes(min)
}
