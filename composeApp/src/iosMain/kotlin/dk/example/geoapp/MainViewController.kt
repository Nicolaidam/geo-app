package dk.example.geoapp

import androidx.compose.ui.window.ComposeUIViewController
import dk.example.geoapp.api.GeoApiClientImpl

fun MainViewController() = ComposeUIViewController {
    App(api = GeoApiClientImpl(
        rawBaseUrl = "http://localhost:8089",
    ))
}
