package dk.example.geoapp

import io.ktor.client.engine.HttpClientEngine

expect fun httpClientEngine(): HttpClientEngine


expect fun locationService(): LocationService
