package section5

import kotlinx.coroutines.*


fun main() = runBlocking<Unit> {
    val async = async(Dispatchers.IO) {
        delay(1000)
        return@async "dummy"
    }

    val await = async.await()
    println(await)

}