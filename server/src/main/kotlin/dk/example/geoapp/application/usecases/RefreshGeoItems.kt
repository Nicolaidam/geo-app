package dk.example.geoapp.application.usecases

import dk.example.geoapp.domain.GeoItemCache
import dk.example.geoapp.domain.GeofaApiClient

class RefreshGeoItems(
    private val geofaApiClient: GeofaApiClient,
    private val cache: GeoItemCache
) {
    suspend operator fun invoke(): Result<Unit> =
        geofaApiClient.fetchGeoItems(types = emptyList()).fold(
            onSuccess = { cache.save(it) },
            onFailure = { Result.failure(it) }
        )
}
