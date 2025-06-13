package dk.example.geoapp.infrastructure.config

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.engine.applicationEnvironment

class AppConfig {
    val jdbcUrl = applicationEnvironment {
        config.config("jdbcUrl")
    }
//    val username = environment.getProperty("app.database.username")
//    val password = environment.getProperty("app.database.password")
}
