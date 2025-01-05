package section12

import kotlinx.coroutines.*

class StringStateHolder(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val coroutineScope = CoroutineScope(dispatcher)

    var stringState = ""
        private set

    fun updateStringWithDelay(string: String) {
        coroutineScope.launch {
            delay(timeMillis = 1000L)
            stringState = string
        }
    }
}
