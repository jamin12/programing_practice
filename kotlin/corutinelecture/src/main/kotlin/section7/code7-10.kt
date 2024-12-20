package section7

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking<Unit> { // 루트 Job 생성
    val newScope = CoroutineScope(Dispatchers.IO) // 새로운 루트 Job 생성

    newScope.launch(CoroutineName(name = "Coroutine1")) { // Coroutine1
        launch(CoroutineName(name = "Coroutine3")) { // Coroutine3
            delay(timeMillis = 100L)
            println("[${Thread.currentThread().name}] 코루틴 실행")
        }
        launch(CoroutineName(name = "Coroutine4")) { // Coroutine4
            delay(timeMillis = 100L)
            println("[${Thread.currentThread().name}] 코루틴 실행")
        }
    }

    newScope.launch(CoroutineName(name = "Coroutine2")) { // Coroutine2
        launch(CoroutineName(name = "Coroutine5") + Job()) { // Coroutine5
            delay(timeMillis = 100L)
            println("[${Thread.currentThread().name}] 코루틴 실행")
        }
    }
    newScope.cancel()
    delay(1000)
}
