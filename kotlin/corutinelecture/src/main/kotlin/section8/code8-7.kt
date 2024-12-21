package section8

import kotlinx.coroutines.*

fun main() = runBlocking {
    CoroutineScope(Dispatchers.Default).launch {
        throw Exception("This exception will not affect the parent")
    }
    println("Parent continues")
}