package uba.fi.goodreads.data.auth.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.request.LoginBody
import uba.fi.goodreads.data.auth.request.RegisterBody
import uba.fi.goodreads.data.core.BetterReadsClient
import uba.fi.goodreads.data.core.BrDispatchers
import uba.fi.goodreads.data.core.Dispatcher
import uba.fi.goodreads.data.core.ResponseHandler
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val userSessionLocalDataSource: UserSessionLocalDataSource,
    private val client: BetterReadsClient,
    private val responseHandler: ResponseHandler,
    @Dispatcher(BrDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : SessionRepository {

    override val isUserLogged: Flow<Boolean> = userSessionLocalDataSource.isUserLogged

    override suspend fun listenForUnauthorizedResponses() {
        responseHandler.unauthorizedFlow.collect { isUnauthorized ->
            if (isUnauthorized) {
                logOutUser()
            }
        }
    }

    private suspend fun logOutUser() {
        clearUserId()
    }

    override suspend fun saveUserId(accessToken: String) {
        withContext(ioDispatcher) {
            userSessionLocalDataSource.saveAccessToken(accessToken)
        }
    }

    override suspend fun clearUserId() {
        withContext(ioDispatcher) {
            userSessionLocalDataSource.clearAccessToken()
        }
    }

    override suspend fun getUserId(): String {
        return withContext(ioDispatcher) {
            val token = userSessionLocalDataSource.getAccessToken()
            token
        }
    }

    override suspend fun login(
        email: String,
        password: String,
    ): NetworkResult<Unit> {
        return responseHandler {
            saveUserId(
                client.logIn(
                    LoginBody(
                        email = email,
                        password = password
                    )
                ).userId.toString()
            )
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        isAuthor: Boolean
    ): NetworkResult<Unit> {
        return responseHandler {
            saveUserId(
            client.register(
                RegisterBody(
                    email = email,
                    password = password,
                    firstName = firstName,
                    lastName = lastName,
                    isAuthor = isAuthor
                )
            ).userId.toString()
            )
        }
    }
}
