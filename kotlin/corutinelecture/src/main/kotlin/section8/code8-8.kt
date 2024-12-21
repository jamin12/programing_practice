package section8

import kotlinx.coroutines.*

fun main() = runBlocking {
    val a = 0
    val deferred = async {
        try {
            if (a == 0) {
                1
            } else {
                throw Exception("Async exception occurred")
            }
        } catch (e: Exception) {
            println("Caught in async block: ${e.message}")
        }
    }

    val job = launch {
        println("Sibling coroutine is running")
        delay(1000)
        println("Sibling coroutine completed")
    }

    try {
        println(deferred.await())
    } catch (e: Exception) {
        println("Caught in parent: ${e.message}")
    }

    job.join()
    println("Parent coroutine completed")
}
