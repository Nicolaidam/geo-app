package dk.example.geoapp

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

actual fun httpClientEngine(): HttpClientEngine = OkHttp.create()

actual fun locationService(): LocationService {
    // Desktop/JVM has no standard geolocation API, so we expose a
    // very small stub that always reports “authorized” permission and
    // returns `null` for the current location.
    //
    // If you later add an IP‑based or external GPS library, plug it in here.
    return object : LocationService {
        private val _permissionFlow = MutableStateFlow(LocationPermission.authorized)

        override val locationPermissionOnChange: Flow<LocationPermission>
            get() = _permissionFlow

        override fun getLocationPermission(): LocationPermission = _permissionFlow.value

        // No runtime permission concept on the JVM desktop, so this is a no‑op.
        override fun requestLocationPermission() = Unit

        // JVM (desktop) has no built‑in location hardware; return null.
        override fun getLocation(): Location? = null
    }
}
