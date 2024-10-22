package uba.fi.goodreads.data.core

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val responseBuilder = chain.request().newBuilder()

        runBlocking {
            responseBuilder.addHeader(HEADER_AUTHORIZATION, sessionRepository.getAccessToken())
        }

        return chain.proceed(responseBuilder.build())
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Auth"
    }

}