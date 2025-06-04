package dk.example.geoapp.geoapi

import dk.example.geoapp.model.GeoItemDto

class CachedGeoApi(private val wrapped: GeoApi) : GeoApi {

    private val ttlMillis = 24 * 60 * 60 * 1000 // 24 hours in milliseconds

    override suspend fun fetchData(types: List<String>): Result<List<GeoItemDto>> {
        val now = System.currentTimeMillis()
        // update so it uses TYPES instead of TYPE
//        val cacheEntry = cache["1012"]
//        return if (cacheEntry != null && (now - cacheEntry.timestamp) < ttlMillis) {
//            Result.success(cacheEntry.items)
//        } else {
            val result = wrapped.fetchData(types = types)
//            result.onSuccess {
//                cache["1012"] = CacheEntry(items = it, timestamp = now)
//            }
            return result
//        }
    }
}
