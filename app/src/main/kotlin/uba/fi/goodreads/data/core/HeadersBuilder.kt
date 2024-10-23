package uba.fi.goodreads.data.core

import okhttp3.Request

class HeadersBuilder() {

    fun build(requestBuilder: Request.Builder): Request.Builder {
        val headersBuilder = requestBuilder
            .addHeader(HEADER_ACCEPT, ACCEPT_APPLICATION_JSON)
            .addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)

        return headersBuilder
    }

    companion object {
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_JSON = "application/json"
        private const val HEADER_ACCEPT = "accept"
        private const val ACCEPT_APPLICATION_JSON = "application/json"

    }
}