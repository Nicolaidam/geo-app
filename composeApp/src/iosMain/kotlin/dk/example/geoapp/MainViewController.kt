package dk.example.geoapp

import androidx.compose.ui.window.ComposeUIViewController
import dk.example.geoapp.infrastructure.GeoApiClientImpl

fun MainViewController() = ComposeUIViewController {
    App(api = GeoApiClientImpl(
        rawBaseUrl = "http://localhost:8080",
    ))
}
