package section7

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val runblockingJob = coroutineContext[Job]
    launch {
        val launchJob = coroutineContext[Job]
        if (runblockingJob === launchJob) {
            print("같음")
        } else {
            print("다름")
        }
    }
}