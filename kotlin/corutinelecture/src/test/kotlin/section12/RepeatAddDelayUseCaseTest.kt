package section12

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class RepeatAddDelayUseCaseTest {

    @Test
    fun add() = runBlocking {
        // Given
        val repeatAddDelayUseCase = RepeatAddDelayUseCase()

        // When
        val result: Int = repeatAddDelayUseCase.add(100)

        // Then
        assertEquals(100, result)
    }

    @Test
    fun `가상 시간 조절 테스트`() {
        val testCoroutineScheduler = TestCoroutineScheduler()

        testCoroutineScheduler.advanceTimeBy(5000L) // 5초를 흐르게 만듦
        assertEquals(5000L, testCoroutineScheduler.currentTime) // 현재 시간이 5초임을 단언
        testCoroutineScheduler.advanceTimeBy(6000L) // 6초를 흐르게 만듦
        assertEquals(11000L, testCoroutineScheduler.currentTime) // 현재 시간이 11초임을 단언
        testCoroutineScheduler.advanceTimeBy(10000L) // 10초를 흐르게 만듦
        assertEquals(21000L, testCoroutineScheduler.currentTime) // 현재 시간이 21초임을 단언
    }

    @Test
    fun `가상 시간 위에서 테스트 진행`() {
        // Given
        val testCoroutineScheduler = TestCoroutineScheduler()
        val testDispatcher: TestDispatcher = StandardTestDispatcher(scheduler = testCoroutineScheduler)
        val testCoroutineScope = CoroutineScope(context = testDispatcher)

        var result = 0

        // When
        testCoroutineScope.launch {
            delay(timeMillis = 10000L) // 10초간 대기
            result = 1
            delay(timeMillis = 10000L) // 10초간 대기
            result = 2
        }

        // Then
        testCoroutineScheduler.advanceTimeBy(delayTimeMillis = 5000L) // 5초를 흐르게 만듦
        assertEquals(0, result)
        testCoroutineScheduler.advanceTimeBy(delayTimeMillis = 6000L) // 6초를 흐르게 만듦
        assertEquals(1, result)
        testCoroutineScheduler.advanceTimeBy(delayTimeMillis = 10000L) // 10초를 흐르게 만듦
        assertEquals(2, result)
    }


    @Test
    fun `advanceUntilIdle 테스트 진행`() {
        // Given
        val testDispatcher: TestDispatcher = StandardTestDispatcher()
        val testCoroutineScope = CoroutineScope(context = testDispatcher)

        var result = 0

        // When
        testCoroutineScope.launch {
            delay(timeMillis = 10000L) // 10초간 대기
            result = 1
            delay(timeMillis = 10000L) // 10초간 대기
            result = 2
        }
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(2, result)
    }

    @Test
    fun `TestScope 테스트 진행`() {
        // Given
        val testScope = TestScope()
        var result = 0

        // When
        testScope.launch {
            delay(timeMillis = 10000L) // 10초간 대기
            result = 1
            delay(timeMillis = 10000L) // 10초간 대기
            result = 2
        }
        testScope.advanceUntilIdle()

        // Then
        assertEquals(2, result)
    }

    @Test
    fun `runTest 테스트 진행`() {
        // Given
        var result = 0

        // When
        runTest {
            delay(timeMillis = 10000L) // 10초간 대기
            result = 1
            delay(timeMillis = 10000L) // 10초간 대기
            result = 2
        }

        // Then
        assertEquals(2, result)
    }


}