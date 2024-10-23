package uba.fi.goodreads.data.core

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor(
    private val headersBuilder: HeadersBuilder,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(addHeaders(chain.request()))
    }

    private fun addHeaders(
        request: Request
    ): Request {
        return headersBuilder.build(request.newBuilder()).build()
    }
}