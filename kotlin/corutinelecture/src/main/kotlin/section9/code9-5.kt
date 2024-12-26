package section9

import kotlinx.coroutines.*

suspend fun example(st: String, time: Long) {
    delay(time) // 1초 동안 중단
    println("작업 시작 $st - ${Thread.currentThread().name}")
    delay(1000) // 1초 동안 중단
    println("작업 재개 $st - ${Thread.currentThread().name}")
}

fun main(): Unit = runBlocking {
    launch(Dispatchers.IO) {
        example("qwer1", 500) // 코루틴 내부에서 호출
    }
    launch(Dispatchers.IO) {
        example("qwer2", 0) // 코루틴 내부에서 호출
    }

    launch(Dispatchers.IO) {
        example("qwer3", 100) // 코루틴 내부에서 호출
    }

    launch(Dispatchers.IO) {
        example("qwer4", 200) // 코루틴 내부에서 호출
    }

    launch(Dispatchers.IO) {
        example("qwer5", 300) // 코루틴 내부에서 호출
    }
}
