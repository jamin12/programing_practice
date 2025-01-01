package section11

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger


fun main(): Unit = runBlocking<Unit> {
    val job: Job = launch(start = CoroutineStart.ATOMIC) {
        println("작업1")
    }
    job.cancel() // 생성 상태의 코루틴에 취소 요청
    println("작업2")
}
