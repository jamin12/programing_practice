package section4.code1

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val launch = launch(Dispatchers.Default) {
        delay(100)
    }
    println(jobStatus(launch))

    val lazyLaunch = launch(start = CoroutineStart.LAZY) {
        delay(100)
    }
    println(jobStatus(lazyLaunch))

    val launchCompleted = launch(Dispatchers.Default) {
        delay(100)
    }
    launchCompleted.join()
    println(jobStatus(launchCompleted))

    val cancelLaunch = launch(Dispatchers.Default) {
        delay(100)
    }
    cancelLaunch.cancelAndJoin()
    println(jobStatus(cancelLaunch))
}

fun jobStatus(job: Job) = "jobStatus " +
        " isActive ${job.isActive}" +
        " isCancelled ${job.isCancelled}" +
        " isCompleted ${job.isCompleted}"