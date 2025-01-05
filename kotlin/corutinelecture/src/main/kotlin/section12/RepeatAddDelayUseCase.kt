package section12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RepeatAddDelayUseCase {
    suspend fun add(repeatTime: Int): Int = withContext(Dispatchers.Default) {
        var result = 0
        repeat(repeatTime) {
            delay(100L)
            result += 1
        }
        return@withContext result
    }
}
