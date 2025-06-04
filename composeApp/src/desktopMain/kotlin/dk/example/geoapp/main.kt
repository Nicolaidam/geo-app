package dk.example.geoapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "geo-app",
    ) {
        App(
            api = TODO(),
            viewModel = TODO()
        )
    }
}
