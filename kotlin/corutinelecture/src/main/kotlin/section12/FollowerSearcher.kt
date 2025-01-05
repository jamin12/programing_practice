package section12

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class FollowerSearcher(
    private val officialAccountRepository: OfficialAccountRepository,
    private val personAccountRepository: PersonAccountRepository
) {
    suspend fun searchByName(name: String): List<Follower> = coroutineScope {
        val officialAccountsDeferred: Deferred<Array<Follower.OfficialAccount>> = async {
            officialAccountRepository.searchByName(name)
        }
        val personAccountsDeferred: Deferred<Array<Follower.PersonAccount>> = async {
            personAccountRepository.searchByName(name)
        }

        return@coroutineScope listOf(
            *officialAccountsDeferred.await(),
            *personAccountsDeferred.await()
        )
    }
}