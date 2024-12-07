package section3.code1

import kotlinx.coroutines.*

val multiThreadDispatcher: CoroutineDispatcher = newFixedThreadPoolContext(2, "multiThreadDispatcher")

fun main() = runBlocking<Unit> {
    launch(multiThreadDispatcher) {
        println("${Thread.currentThread().name} - coroutine started")
    }

    launch(multiThreadDispatcher) {
        println("${Thread.currentThread().name} - coroutine started")
    }
}