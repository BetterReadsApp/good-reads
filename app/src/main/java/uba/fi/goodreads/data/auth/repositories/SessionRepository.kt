package uba.fi.goodreads.data.auth.repositories

import kotlinx.coroutines.flow.Flow
import uba.fi.goodreads.core.network.NetworkResult

interface SessionRepository {

    val isUserLogged: Flow<Boolean>

    suspend fun listenForUnauthorizedResponses()

    suspend fun saveAccessToken(accessToken: String)

    suspend fun clearAccessToken()

    suspend fun getAccessToken(): String

    suspend fun login(
        email: String,
        password: String,
    ): NetworkResult<Unit>

    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): NetworkResult<String>
}