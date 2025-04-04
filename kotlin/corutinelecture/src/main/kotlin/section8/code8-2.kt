package section8

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch(CoroutineName(name = "Parent Coroutine")) {
        // 새로운 Job 객체를 만들어 Coroutine1에 연결
        launch(context = CoroutineName(name = "Coroutine1") + Job()) {
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
    delay(timeMillis = 1000L)
}
