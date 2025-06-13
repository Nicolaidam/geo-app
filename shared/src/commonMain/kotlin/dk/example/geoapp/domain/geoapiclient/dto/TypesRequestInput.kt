package dk.example.geoapp.domain.geoapiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class TypesRequestInput(val minimumSize: Int?)
