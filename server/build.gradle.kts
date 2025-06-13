plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    alias(libs.plugins.kotlinxSerialization)
}

group = "dk.example.geoapp"
version = "1.0.0"
application {
    mainClass.set("dk.example.geoapp.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    implementation(libs.ktor.serverCallLogging)
    implementation(libs.ktor.serverCors)
    implementation(libs.ktor.serverContentNegotiation)
    implementation(libs.ktor.serverResources)
    implementation(libs.ktor.serverConfigYaml)
    implementation(libs.ktorClient)
    implementation(libs.ktorClient.contentNegotiation)
    implementation(libs.ktorClient.logging)
    implementation(libs.ktorClient.serialization)
    implementation(libs.ktorClient.logging)
    implementation(libs.ktor.openapi)
    implementation(libs.ktor.swagger.ui)

    testImplementation(libs.kotlin.testJunit)
    testImplementation(libs.ktor.serverTestHost)

//    testImplementation(libs.junit)

}

tasks.withType<Test> {
    useJUnitPlatform()
}
