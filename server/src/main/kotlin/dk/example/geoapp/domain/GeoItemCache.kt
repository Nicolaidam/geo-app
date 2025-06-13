package dk.example.geoapp.domain

import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemTypeDto

interface GeoItemCache {
    suspend fun save(all: List<GeoItemDto>): Result<Unit>
    fun load(types: List<String>): Result<List<GeoItemDto>>
    fun loadTypes(min: Int?): Result<List<GeoItemTypeDto>>
}
