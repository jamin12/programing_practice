package secion4.code1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        println("token thread: ${Thread.currentThread().name}")
        delay(1000)
        println("token thread after delay: ${Thread.currentThread().name}")
    }.join()

    launch(Dispatchers.IO) {
        println("network thread: ${Thread.currentThread().name}")
    }
}