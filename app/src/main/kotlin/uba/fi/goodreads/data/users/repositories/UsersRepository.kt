package uba.fi.goodreads.data.users.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.User

interface UsersRepository {

    suspend fun getUser(id: String, ownId: String): NetworkResult<User>

    suspend fun followUser(id: String): NetworkResult<Unit>

    suspend fun unfollowUser(id: String): NetworkResult<Unit>

    suspend fun searchUsers(text: String): NetworkResult<List<User>>

    suspend fun editUser(
        firstName: String,
        lastName: String,
        email: String,
        avatarUrl: String,
        isAuthor: Boolean,
    ): NetworkResult<Unit>
}