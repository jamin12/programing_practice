package section7

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime: Long = System.currentTimeMillis()
    val parentJob: Job = launch { // 부모 코루틴 실행
        launch { // 자식 코루틴 실행
            delay(timeMillis = 1000L) // 1초간 대기
            println("[${getElapsedTime(startTime)}] 자식 코루틴 실행 완료")
        }
        println("[${getElapsedTime(startTime)}] 부모 코루틴이 실행하는 마지막 코드")
    }
    parentJob.invokeOnCompletion { it: Throwable? -> // 부모 코루틴이 완료될 시 호출되는 콜백 등록
        println("[${getElapsedTime(startTime)}] 부모 코루틴 실행 완료")
    }
    delay(500)
    println(parentJob)
    println(parentJob.isActive)
}

private fun getElapsedTime(startTime: Long): String =
    "지난 시간: ${System.currentTimeMillis() - startTime}ms"

