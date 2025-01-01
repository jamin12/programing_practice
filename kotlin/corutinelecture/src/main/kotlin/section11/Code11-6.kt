package section11

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger


fun main(): Unit = runBlocking<Unit>(Dispatchers.IO) {
    println("runBlocking 코루틴 실행 스레드: ${Thread.currentThread().name}")
    launch {
        println("launch 코루틴 실행 스레드: ${Thread.currentThread().name}")
    }
    println("runBlocking 코루틴 실행 스레드 end: ${Thread.currentThread().name}")
}
