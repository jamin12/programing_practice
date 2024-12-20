package section7

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomCoroutineScope : CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Job() + newSingleThreadContext(name = "CustomScopeThread")
}

fun main() {
    val coroutineScope = CustomCoroutineScope() // CustomCoroutineScope 인스턴스화
    coroutineScope.launch { // 코루틴 실행
        delay(timeMillis = 100L) // 100밀리초 대기
        println("[${Thread.currentThread().name}] 코루틴 실행 완료")
    }
    Thread.sleep(1000L) // 코드 종료 방지
}
