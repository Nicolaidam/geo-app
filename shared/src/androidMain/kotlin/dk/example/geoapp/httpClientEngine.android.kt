package dk.example.geoapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun httpClientEngine(): HttpClientEngine {
    return OkHttp.create()
}
actual fun locationService(): LocationService {
    return object : LocationService {
        private val _permissionFlow = MutableStateFlow(getLocationPermission())

        override val locationPermissionOnChange: Flow<LocationPermission>
            get() = _permissionFlow

        override fun getLocationPermission(): LocationPermission {
            TODO()
        }

        override fun requestLocationPermission() {
            TODO()
        }

        override fun getLocation(): dk.example.geoapp.Location? {
            TODO()
        }
    }
}
