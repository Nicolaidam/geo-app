package dk.example.geoapp.geoapi

import dk.example.geoapp.model.GeoItemDto

interface GeoApi {
    suspend fun fetchData(types: List<String>): Result<List<GeoItemDto>>
}
