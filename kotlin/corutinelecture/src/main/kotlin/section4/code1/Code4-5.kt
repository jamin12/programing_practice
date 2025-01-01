package section4.code1

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val launch = launch(Dispatchers.Default) {
        repeat(10) {repeatTime ->
            delay(1000)
            println("${getElapsedTime(startTime)} : ${repeatTime}")
        }
    }
}