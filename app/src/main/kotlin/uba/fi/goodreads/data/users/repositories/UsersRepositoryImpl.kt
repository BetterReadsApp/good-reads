package uba.fi.goodreads.data.users.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.core.BetterReadsClient
import uba.fi.goodreads.data.core.ResponseHandler
import uba.fi.goodreads.domain.model.User
import javax.inject.Inject

internal class UsersRepositoryImpl @Inject constructor(
    private val client: BetterReadsClient,
    private val responseHandler: ResponseHandler,
): UsersRepository {

    override suspend fun getUser(id: String): NetworkResult<User> {
        return responseHandler {
            client.getUser(userId = id).toDomain()
        }
    }

    override suspend fun searchUsers(text: String): NetworkResult<List<User>> {
        return responseHandler {
            client.searchUsers(text = text).map { it.toDomain() }
        }
    }
}