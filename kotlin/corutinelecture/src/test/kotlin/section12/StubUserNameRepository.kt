package section12

class StubUserNameRepository(
    private val userNameMap: Map<String, String>
) : UserNameRepository {

    override fun saveUserName(id: String, name: String) {
        // 구현하지 않는다.
    }

    override fun getNameByUserId(id: String): String {
        return userNameMap[id] ?: ""
    }
}
