package section3.code1

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

val singleThreadDispatcher: CoroutineDispatcher = newSingleThreadContext("singleThread")

fun main() = runBlocking<Unit> {
    launch(singleThreadDispatcher) {
        println("${Thread.currentThread().name} - coroutine started")
    }

}