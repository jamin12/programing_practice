package section7

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val runblockingJob = coroutineContext[Job]
    launch {
        val launchJob = coroutineContext[Job]
        println(launchJob?.parent === runblockingJob)
        println(runblockingJob?.children?.contains(launchJob))
    }
}