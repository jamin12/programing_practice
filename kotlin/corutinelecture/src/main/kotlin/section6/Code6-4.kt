package section6

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val coroutineName1 = CoroutineName("MyCoroutine")
    val io = Dispatchers.IO
    val coroutineContext = io + coroutineName1
    val coroutineName = coroutineContext[io.key]

    println(coroutineName)
}
