package section7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val coroutineContext = newSingleThreadContext("MyThread") + CoroutineName("CoroutineA")
    launch(coroutineContext) {
        println("${Thread.currentThread().name} 부모 코루틴 ")
        launch(CoroutineName("childCoroutine")) {
            println("${Thread.currentThread().name} 자식 코루틴")
        }
    }
}