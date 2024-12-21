package section8

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    supervisorScope {
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
    }
}
