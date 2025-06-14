package dk.example.geoapp.infrastructure.scheduler

import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Starts a scheduled job that invokes [refreshAction] once every day.
 */
fun startDailyRefreshJob(refreshAction: suspend () -> Unit) {
    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate({
        runBlocking {
            try {
                refreshAction()
            } catch (_: Throwable) {
                // Swallow exceptions to keep scheduler alive
            }
        }
    }, 0, 1, TimeUnit.DAYS)
}
