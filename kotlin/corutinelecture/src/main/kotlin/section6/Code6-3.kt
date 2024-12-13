package section6

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val coroutineContext = newSingleThreadContext("myThread") + CoroutineName("MyCoroutine")
    val coroutineName = coroutineContext[CoroutineName]

    println(coroutineName)
}
