package section12

import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class StringStateHolderTest {

    @Test
    fun `updateStringWithDelay(ABC)가 호출되면 문자열이 ABC로 변경된다`() {
        // Given
        val testDispatcher = StandardTestDispatcher()
        val stringStateHolder = StringStateHolder(
            dispatcher = testDispatcher
        )

        // When
        stringStateHolder.updateStringWithDelay(string = "ABC")

        // Then
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals("ABC", stringStateHolder.stringState)
    }

}