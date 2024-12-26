package section9

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun main() {
    de()
}


suspend fun de() {
    delay(1000)
    println("hello")
}