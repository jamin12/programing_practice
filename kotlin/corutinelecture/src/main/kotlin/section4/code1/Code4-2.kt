package section4.code1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val launch = launch(Dispatchers.Default) {
        println("token1 thread start: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("token1 thread after delay: ${Thread.currentThread().name}")
    }

    val launch1 = launch(Dispatchers.Default) {
        println("token2 thread start: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("token2 thread after delay: ${Thread.currentThread().name}")
    }

    launch.join()
    launch1.join()

    launch(Dispatchers.IO) {
        println("network thread: ${Thread.currentThread().name}")
    }
}