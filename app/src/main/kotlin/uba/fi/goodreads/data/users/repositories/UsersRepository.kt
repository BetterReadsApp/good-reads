package uba.fi.goodreads.data.users.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.User

interface UsersRepository {
    suspend fun getUser(id: String): NetworkResult<User>
}