package section8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch(CoroutineName(name = "Coroutine1")) {
        launch(CoroutineName(name = "Coroutine3")) {
            throw Exception("예외 발생")
        }
        delay(timeMillis = 100L)
        println("[${Thread.currentThread().name}] 코루틴 실행")
    }
    launch(CoroutineName(name = "Coroutine2")) {
        delay(timeMillis = 100L)
        println("[${Thread.currentThread().name}] 코루틴 실행")
    }
    delay(timeMillis = 1000L)
}
