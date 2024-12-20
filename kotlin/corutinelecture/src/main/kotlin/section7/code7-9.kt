package section7

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking<Unit> {
    launch(CoroutineName(name = "Coroutine1")) { // Coroutine1
        launch(CoroutineName(name = "Coroutine3")) { // Coroutine3
            delay(timeMillis = 100L)
            println("[${Thread.currentThread().name}] 1코루틴 실행 완료")
        }
        launch(CoroutineName(name = "Coroutine4")) { // Coroutine4
            delay(timeMillis = 100L)
            println("[${Thread.currentThread().name}] 2코루틴 실행 완료")
        }
        this.cancel() // Coroutine1의 CoroutineScope에 cancel 요청
    }

    launch(CoroutineName(name = "Coroutine2")) { // Coroutine2
        delay(timeMillis = 100L)
        println("[${Thread.currentThread().name}] 3코루틴 실행 완료")
    }
    println("[${Thread.currentThread().name}] 4코루틴 실행 완료")
}