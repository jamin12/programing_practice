package section12

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class AddUseCaseTest {
    private lateinit var addUseCase: AddUseCase

    @BeforeEach
    fun setUp() {
        addUseCase = AddUseCase()

    }

    @Test
    fun add123() {
        // when
        val result = addUseCase.add(1, 2, 3)

        // then
        Assertions.assertEquals(6, result)
    }

    @Test
    fun add123Fail() {
        // when
        val result = addUseCase.add(1, 2, 3)

        // then
        Assertions.assertNotEquals(5, result)
    }
}