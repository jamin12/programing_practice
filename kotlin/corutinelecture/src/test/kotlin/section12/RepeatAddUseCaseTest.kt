package section12

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class RepeatAddUseCaseTest {

    @Test
    fun add() = runBlocking {
        // Given
        val repeatAddUseCase = RepeatAddUseCase()

        // When
        val result: Int = repeatAddUseCase.add(100)

        // Then
        assertEquals(100, result)
    }
}