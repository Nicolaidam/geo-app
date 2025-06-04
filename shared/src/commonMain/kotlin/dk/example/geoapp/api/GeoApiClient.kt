package dk.example.geoapp.api

import dk.example.geoapp.model.Response
import dk.example.geoapp.model.GeoItemDto

interface GeoApiClient {
    suspend fun getGeoItems(): Response<List<GeoItemDto>>
}
