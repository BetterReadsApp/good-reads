package uba.fi.goodreads.data.auth.repositories

import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    val isUserLogged: Flow<Boolean>

    suspend fun listenForUnauthorizedResponses()

    suspend fun saveAccessToken(accessToken: String)

    suspend fun clearAccessToken()

    suspend fun getAccessToken(): String
}