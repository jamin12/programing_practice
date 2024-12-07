package section3.code1

import kotlinx.coroutines.*


fun main() = runBlocking<Unit> {
    val limitedParallelism = Dispatchers.IO.limitedParallelism(2)
    repeat(100) {
        launch(limitedParallelism) {
            launch {
                Thread.sleep(1000)
                println("${Thread.currentThread().name} - 처리완료")
            }
        }
    }
}