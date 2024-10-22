package uba.fi.goodreads.data.auth.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uba.fi.goodreads.data.core.BrDispatchers
import uba.fi.goodreads.data.core.Dispatcher
import uba.fi.goodreads.data.core.ResponseHandler
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val userSessionLocalDataSource: UserSessionLocalDataSource,
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
        clearAccessToken()
    }

    override suspend fun saveAccessToken(accessToken: String) {
        withContext(ioDispatcher) {
            userSessionLocalDataSource.saveAccessToken(accessToken)
        }
    }

    override suspend fun clearAccessToken() {
        withContext(ioDispatcher) {
            userSessionLocalDataSource.clearAccessToken()
        }
    }

    override suspend fun getAccessToken(): String {
        return withContext(ioDispatcher) {
            val token = userSessionLocalDataSource.getAccessToken()
            token
        }
    }

}