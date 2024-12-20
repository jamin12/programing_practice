package section7

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
    val newScope = CoroutineScope(
        context = CoroutineName(name = "MyCoroutine") + Dispatchers.IO
    )
    newScope.launch(context = CoroutineName(name = "LaunchCoroutine")) { // 코루틴 실행
        println("newScope의 coroutineContext: ${newScope.coroutineContext}")
        println("launch 코루틴의 coroutineContext: ${this.coroutineContext}")
        println("launch 코루틴의 parentJob: ${this.coroutineContext[Job]?.parent}")
    }
    Thread.sleep(1000L) // 코드 종료 방지
}
