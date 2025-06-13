package dk.example.geoapp.domain.geoapiclient.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetItemsRequestInput(val types: List<String>)
