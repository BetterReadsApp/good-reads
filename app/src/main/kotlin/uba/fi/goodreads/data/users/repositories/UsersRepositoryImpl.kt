package uba.fi.goodreads.data.users.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.core.BetterReadsClient
import uba.fi.goodreads.data.core.ResponseHandler
import uba.fi.goodreads.data.users.request.EditUserBody
import uba.fi.goodreads.domain.model.User
import javax.inject.Inject

internal class UsersRepositoryImpl @Inject constructor(
    private val client: BetterReadsClient,
    private val responseHandler: ResponseHandler,
) : UsersRepository {

    override suspend fun getUser(id: String, ownId: String): NetworkResult<User> {
        return responseHandler {
            client.getUser(userId = id).toDomain(ownId)
        }
    }

    override suspend fun searchUsers(text: String): NetworkResult<List<User>> {
        return responseHandler {
            client.searchUsers(text = text).map { it.toDomain() }
        }
    }

    override suspend fun followUser(id: String): NetworkResult<Unit> {
        return responseHandler {
            client.followUser(id)
        }
    }

    override suspend fun unfollowUser(id: String): NetworkResult<Unit> {
        return responseHandler {
            client.unfollowUser(id)
        }
    }

    override suspend fun editUser(
        firstName: String,
        lastName: String,
        email: String,
        avatarUrl: String,
        isAuthor: Boolean,
    ): NetworkResult<Unit> {
        return responseHandler {
            client.editUser(
                EditUserBody(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    avatarUrl = avatarUrl,
                    isAuthor = isAuthor
                )
            )
        }
    }
}