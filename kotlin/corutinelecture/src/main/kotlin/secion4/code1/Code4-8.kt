package secion4.code1

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val launch = launch(Dispatchers.Default) {
        while (this.isActive) {
            println(getElapsedTime(startTime))
//            delay(1)
//            yield()
        }
    }
    delay(100)
    launch.cancel()
}