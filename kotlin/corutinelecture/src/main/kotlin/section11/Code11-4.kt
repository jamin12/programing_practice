package section11

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger


var count2 = AtomicInteger()

fun main(): Unit = runBlocking<Unit> {
    withContext(Dispatchers.Default) {
        repeat(10_000) {
            launch {
                count2.getAndUpdate { it + 1 }
            }
        }
    }
    println("count = ${count2.get()}")
}
