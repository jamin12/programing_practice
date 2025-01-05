package section12

interface OfficialAccountRepository {
    suspend fun searchByName(name: String): Array<Follower.OfficialAccount>
}
