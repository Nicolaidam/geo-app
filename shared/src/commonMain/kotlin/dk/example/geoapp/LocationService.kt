package dk.example.geoapp

import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getLocationPermission(): LocationPermission
    fun requestLocationPermission()
    val locationPermissionOnChange: Flow<LocationPermission>
    fun getLocation(): Location?
}

enum class LocationPermission {
    authorized, denied, notDetermined, restricted
}

data class Location (
    val latitude: Double,
    val longitude: Double
)
