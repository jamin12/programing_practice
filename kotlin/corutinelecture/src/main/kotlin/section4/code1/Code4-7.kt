package section4.code1

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val launch = launch(Dispatchers.Default) {
//        Thread.sleep(1000)
        delay(1000)
        println(getElapsedTime(startTime))
    }
    launch.cancelAndJoin()
    println("Coroutine cancelled")
}