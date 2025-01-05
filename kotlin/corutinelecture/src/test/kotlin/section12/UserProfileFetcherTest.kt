package section12

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class UserProfileFetcherTest {


    @Test
    fun getUserProfileById() {
        // Given
        val userProfileFetcher = UserProfileFetcher(
            userNameRepository = StubUserNameRepository(
                userNameMap = mapOf(
                    "0x1111" to "홍길동",
                    "0x2222" to "조세영"
                )
            ),
            userPhoneNumberRepository = FakeUserPhoneNumberRepository().apply {
                this.saveUserPhoneNumber(id = "0x1111", phoneNumber = "010-xxxx-xxxx")
            }
        )
        // When
        val userProfile: UserProfile = userProfileFetcher.getUserProfileById(id = "0x1111")

        // Then
        assertEquals("홍길동", userProfile.name)
        assertEquals("010-xxxx-xxxx", userProfile.phoneNumber)
    }
}