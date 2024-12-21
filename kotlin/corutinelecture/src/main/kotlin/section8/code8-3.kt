package section8

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val supervisorJob: CompletableJob = SupervisorJob(this.coroutineContext[Job])
    launch(context = CoroutineName(name = "Coroutine1") + supervisorJob) {
        launch(CoroutineName(name = "Coroutine3")) {
            throw Exception("예외 발생")
        }
        delay(timeMillis = 100L)
        println("[${Thread.currentThread().name}] 코루틴 1실행")
    }
    launch(context = CoroutineName(name = "Coroutine2") + supervisorJob) {
        delay(timeMillis = 100L)
        println("[${Thread.currentThread().name}] 코루틴 2실행")
    }
    supervisorJob.complete()
}
