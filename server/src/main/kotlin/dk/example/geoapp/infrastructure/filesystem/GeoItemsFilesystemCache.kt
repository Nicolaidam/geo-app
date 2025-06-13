package dk.example.geoapp.infrastructure.filesystem

import dk.example.geoapp.domain.GeoItemCache
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemTypeDto
import java.io.File
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory


class GeoItemsFilesystemCache(
    private val cacheDir: File,
    private val cacheFileName: String,
) : GeoItemCache {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val cacheFile = File(cacheDir, cacheFileName)
    private val json = Json { prettyPrint = true }

    override suspend fun save(all: List<GeoItemDto>): Result<Unit> = runCatching {
        if (!cacheDir.exists()) cacheDir.mkdirs()
        cacheFile.writeText(json.encodeToString(all))
        logger.info("Wrote {} geo items to {}", all.size, cacheFile.absolutePath)
    }

    override fun load(types: List<String>): Result<List<GeoItemDto>> = runCatching {
        val items = readAll()
        if (types.isEmpty()) items else items.filter { it.geoItemType.id in types }
    }

    override fun loadTypes(min: Int?): Result<List<GeoItemTypeDto>> = runCatching {
        readAll()
            .groupingBy { it.geoItemType }
            .eachCount()
            .filter { min == null || it.value >= min }
            .keys
            .toList()
    }

    private fun readAll(): List<GeoItemDto> {
        require(cacheFile.exists()) { "Geo data cache not found" }
        return json.decodeFromString(cacheFile.readText())
    }
}
