package section12

class UserProfileFetcher(
    private val userNameRepository: UserNameRepository,
    private val userPhoneNumberRepository: UserPhoneNumberRepository
) {
    fun getUserProfileById(id: String): UserProfile {
        // 유저의 이름을 UserNameRepository로부터 가져오기
        val userName: String = userNameRepository.getNameByUserId(id)
        // 유저의 전화번호를 UserPhoneNumberRepository로부터 가져오기
        val userPhoneNumber: String = userPhoneNumberRepository.getPhoneNumberByUserId(id)
        return UserProfile(
            id = id,
            name = userName,
            phoneNumber = userPhoneNumber
        )
    }
}
