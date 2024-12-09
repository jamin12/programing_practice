package section5

import kotlinx.coroutines.*
import secion4.code1.getElapsedTime


fun main() = runBlocking<Unit> {
    val startTime: Long = System.currentTimeMillis()
    val participantDeferred1 = async(Dispatchers.IO) {
        delay(1000L)
        return@async arrayOf("철수", "영수")
    }

    val participantDeferred2 = async(Dispatchers.IO) {
        delay(1000L)
        return@async arrayOf("영희")
    }
    val await1 = participantDeferred2.await()
    val await = participantDeferred1.await()

    println("[${getElapsedTime(startTime)}] 참여자 목록: ${listOf(*await, *await1)}")
}