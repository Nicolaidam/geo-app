package dk.example.geoapp

import dk.example.geoapp.infrastructure.filesystem.GeoItemsFilesystemCache
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemDto
import dk.example.geoapp.domain.geoapiclient.dto.GeoItemTypeDto
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import java.util.UUID

/**
 * Unit-tests for [GeoItemsFilesystemCache].
 *
 * The cache no longer reaches out to the network itself; we therefore
 * work exclusively with the three public cache operations:
 *   – save(all)
 *   – load(types)
 *   – loadTypes(min)
 */
class GeoItemsFilesystemCacheTest {

    /* ---------- helpers -------------------------------------------------- */

    private fun geoItem(typeId: String, name: String) = GeoItemDto(
        id              = UUID.randomUUID().toString(),
        name            = name,
        description     = null,
        latitude        = 55.0,
        longitude       = 12.0,
        paymentRequired = false,
        geoItemType  = GeoItemTypeDto(typeId, name),
        photoUrls       = emptyList()
    )

    private fun cache(dir: Path): GeoItemsFilesystemCache =
        GeoItemsFilesystemCache(
            cacheDir       = dir.toFile(),
            cacheFileName  = "response_cache.json"
        )

    private val json = Json { prettyPrint = true }

    /* ---------- save() --------------------------------------------------- */

    @Test fun `save writes cache file`(@TempDir dir: Path) = runBlocking {
        val file = dir.resolve("response_cache.json").toFile()
        val cache = cache(dir)

        assertFalse(file.exists())

        val items = listOf( geoItem("1012", "Toilet") )
        cache.save(items).getOrThrow()

        assertTrue(file.exists())
        assertTrue(file.readText().contains("\"1012\""))
    }

    @Test fun `save overwrites existing file`(@TempDir dir: Path) = runBlocking {
        val c = cache(dir)

        c.save(listOf(geoItem("1012", "Old"))).getOrThrow()
        c.save(listOf(geoItem("3012", "New"))).getOrThrow()

        val txt = dir.resolve("response_cache.json").toFile().readText()
        assertFalse(txt.contains("\"1012\""))
        assertTrue(txt.contains("\"3012\""))
    }

    /* ---------- load() --------------------------------------------------- */

    @Test fun `load filters by single type`(@TempDir dir: Path) = runBlocking {
        val c = cache(dir)
        c.save(
            listOf(
                geoItem("1012", "Toilet"),
                geoItem("3012", "Shelter")
            )
        )

        val result = c.load(listOf("1012")).getOrThrow()

        assertEquals(1, result.size)
        assertEquals("1012", result.first().geoItemType.id)
    }

    @Test fun `load with multiple filters returns union`(@TempDir dir: Path) = runBlocking {
        val c = cache(dir)
        c.save(
            listOf(
                geoItem("1012", "Toilet"),
                geoItem("3012", "Shelter"),
                geoItem("1371", "Bench")
            )
        )

        val ids = c.load(listOf("1012", "1371"))
            .getOrThrow()
            .map { it.geoItemType.id }
            .toSet()

        assertEquals(setOf("1012", "1371"), ids)
    }

    @Test fun `load with empty filter returns all items`(@TempDir dir: Path) = runBlocking {
        val items = listOf(geoItem("1012", "Toilet"))
        val c = cache(dir)
        c.save(items)

        val loaded = c.load(emptyList()).getOrThrow()
        assertEquals(items.size, loaded.size)
    }

    @Test fun `load on missing cache file fails fast`(@TempDir dir: Path) {
        val ex = assertThrows(IllegalArgumentException::class.java) {
            cache(dir).load(listOf("1012")).getOrThrow()
        }
        assertTrue(ex.message!!.contains("Geo data cache not found"))
    }

    /* ---------- loadTypes() --------------------------------------------- */

    @Test fun `loadTypes returns distinct types with counts above min`(@TempDir dir: Path) = runBlocking {
        val c = cache(dir)
        c.save(
            listOf(
                geoItem("1012", "Toilet"),
                geoItem("1012", "Toilet"),
                geoItem("3012", "Shelter")
            )
        )

        val types = c.loadTypes(2).getOrThrow()

        assertEquals(1, types.size)
        assertEquals("1012", types.first().id)
    }
}
