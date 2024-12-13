package section6

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val coroutineContext = newSingleThreadContext("myThread") + CoroutineName("MyCoroutine")
    val newCoroutineContext = coroutineContext + CoroutineName("newCoroutine")

    launch(context = newCoroutineContext) {
        println("${Thread.currentThread().name} - coroutine started")
    }
}
