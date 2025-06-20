package dk.example.geoapp.infrastructure.ktor

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.request.path
import org.slf4j.event.Level

/**
 * Configures call logging for the application.
 * This allows logging of incoming requests.
 */
fun Application.configureCallLogging() {
    install(CallLogging) {
        level = Level.WARN
        filter { call -> call.request.path().startsWith("/") }
    }
}
