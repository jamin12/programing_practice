package section4.code1

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val launch = launch(Dispatchers.Default) {
        delay(100)
    }
    println(launch)

    val lazyLaunch = launch(start = CoroutineStart.LAZY) {
        delay(100)
    }
    println(lazyLaunch)

    val launchCompleted = launch(Dispatchers.Default) {
        delay(100)
    }
    launchCompleted.join()
    println(launchCompleted)

    val cancelLaunch = launch(Dispatchers.Default) {
        delay(100)
    }
    cancelLaunch.cancelAndJoin()
    println(cancelLaunch)
}