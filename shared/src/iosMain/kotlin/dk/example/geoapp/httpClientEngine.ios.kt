package dk.example.geoapp

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSObject

actual fun httpClientEngine(): HttpClientEngine = Darwin.create()

@OptIn(ExperimentalForeignApi::class)
private class IOSLocationDelegate : NSObject(), CLLocationManagerDelegateProtocol {
    val manager = CLLocationManager().also { it.delegate = this }

    private val _permissionFlow = MutableStateFlow(getLocationPermission())
    val permissionFlow: Flow<LocationPermission> get() = _permissionFlow

    fun getLocationPermission(): LocationPermission =
        CLLocationManager.authorizationStatus().toKmpEnum()

    fun requestAuthorization() {
        manager.requestWhenInUseAuthorization()
    }

    fun currentLocation(): Location? {
        val clLocation = manager.location ?: return null
        val coord = clLocation.coordinate
        return coord.useContents {
            Location(
                latitude = latitude,
                longitude = longitude
            )
        }
    }

    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
        _permissionFlow.value = didChangeAuthorizationStatus.toKmpEnum()
        if (_permissionFlow.value == LocationPermission.authorized) {
            manager.startUpdatingLocation()
        } else {
            manager.stopUpdatingLocation()
        }
    }
}

private class IOSLocationService(
    private val delegate: IOSLocationDelegate = IOSLocationDelegate()
) : LocationService {

    override val locationPermissionOnChange: Flow<LocationPermission>
        get() = delegate.permissionFlow

    override fun getLocationPermission(): LocationPermission =
        delegate.getLocationPermission()

    override fun requestLocationPermission() =
        delegate.requestAuthorization()

    override fun getLocation(): Location? =
        delegate.currentLocation()
}

actual fun locationService(): LocationService = IOSLocationService()

/** Convert iOS CoreLocation permission constants to common [LocationPermission] values. */
internal fun CLAuthorizationStatus.toKmpEnum(): LocationPermission = when (this) {
    platform.CoreLocation.kCLAuthorizationStatusNotDetermined -> LocationPermission.notDetermined
    platform.CoreLocation.kCLAuthorizationStatusRestricted,
    platform.CoreLocation.kCLAuthorizationStatusDenied -> LocationPermission.denied
    platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways,
    platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse,
    platform.CoreLocation.kCLAuthorizationStatusAuthorized -> LocationPermission.authorized
    else -> LocationPermission.notDetermined
}
