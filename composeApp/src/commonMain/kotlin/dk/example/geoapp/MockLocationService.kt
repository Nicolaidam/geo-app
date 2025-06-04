package dk.example.geoapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockLocationService(
    private val initialPermission: LocationPermission,
) : LocationService {

    override val locationPermissionOnChange: Flow<LocationPermission> = flow {
        emit(initialPermission)
    }

    override fun getLocationPermission(): LocationPermission = initialPermission

    override fun requestLocationPermission() {}

    override fun getLocation(): Location? {
        return Location(latitude = 1.0, longitude = 1.0)
    }
}

