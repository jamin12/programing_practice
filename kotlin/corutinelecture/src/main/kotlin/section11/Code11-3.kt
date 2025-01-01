package section11

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


fun main(): Unit = runBlocking<Unit> {
    withContext(Dispatchers.Default.limitedParallelism(1)) {
        repeat(10_000) {
            launch {
                count += 1
            }
        }
    }
    println("count = $count")
}
