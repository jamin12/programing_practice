package section7

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking<Unit> {
    launch(CoroutineName(name = "Coroutine1")) { // Coroutine1
        val coroutine1Job: Job? = this.coroutineContext[Job] // Coroutine1의 Job
        val newJob: CompletableJob = Job(parent = coroutine1Job)
        launch(context = CoroutineName(name = "Coroutine2") + newJob) { // Coroutine2
            delay(timeMillis = 100L)
            println("[${Thread.currentThread().name}] 코루틴 실행")
        }
        newJob.complete()
    }
}
