package dk.example.geoapp.domain.geoapiclient

import dk.example.geoapp.domain.geoapiclient.model.GeoItem
import dk.example.geoapp.domain.geoapiclient.model.GeoItemType
import dk.example.geoapp.domain.geoapiclient.model.GeoItemCode

interface GeoApiClient {
    suspend fun fetchGeoItems(types: List<GeoItemCode>): Result<List<GeoItem>>
    suspend fun fetchGeoTypes(minimumSize: Int?): Result<List<GeoItemType>>
}
