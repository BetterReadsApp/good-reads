package uba.fi.goodreads.data.core

import retrofit2.http.Body
import retrofit2.http.POST
import uba.fi.goodreads.data.auth.request.LoginBody
import uba.fi.goodreads.data.auth.response.LoginResponse

internal interface BetterReadsClient {

    @POST("/login")
    suspend fun logIn(@Body body: LoginBody): LoginResponse
}