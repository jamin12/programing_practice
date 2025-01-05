package section12

interface UserPhoneNumberRepository {
    fun saveUserPhoneNumber(id: String, phoneNumber: String)
    fun getPhoneNumberByUserId(id: String): String
}