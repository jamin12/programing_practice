package section3.code1

import kotlinx.coroutines.*


fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        println("${Thread.currentThread().name} - coroutine started1")
    }

    launch(Dispatchers.IO) {
        println("${Thread.currentThread().name} - coroutine started2")
    }

    launch(Dispatchers.IO) {
        println("${Thread.currentThread().name} - coroutine started3")
    }
}