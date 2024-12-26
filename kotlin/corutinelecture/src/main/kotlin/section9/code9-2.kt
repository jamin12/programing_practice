package section9

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking<Unit> {
    val startTime: Long = System.currentTimeMillis()
    delayAndPrintHelloWorld()
    delayAndPrintHelloWorld()
    println(getElapsedTime(startTime))
}

suspend fun delayAndPrintHelloWorld() {
    delay(timeMillis = 1000L)
    println("Hello World")
}

fun getElapsedTime(startTime: Long): String = "지난 시간: ${System.currentTimeMillis() - startTime}ms"
