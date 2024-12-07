package section3.code1

import kotlinx.coroutines.*


fun main() = runBlocking<Unit> {
    launch(Dispatchers.Default) {
        launch {
            println("${Thread.currentThread().name} - coroutine started1")
        }

        launch {
            println("${Thread.currentThread().name} - coroutine started2")
        }

        launch {
            println("${Thread.currentThread().name} - coroutine started3")
        }
    }
}