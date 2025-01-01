package section4.code1

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val launch = launch(start = CoroutineStart.LAZY) {
        println(getElapsedTime(startTime))
    }
    delay(1000)
    launch.start()
}