package dk.example.geoapp.domain

import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto

interface GeofaApiClient {
    suspend fun fetchGeoItems(types: List<String>): Result<List<GeoItemDto>>
}
