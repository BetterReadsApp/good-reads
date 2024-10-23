package uba.fi.goodreads.data.core

import retrofit2.http.Body
import retrofit2.http.POST
import uba.fi.goodreads.data.shelf.request.CreateShelfBody
import uba.fi.goodreads.data.auth.request.LoginBody
import uba.fi.goodreads.data.auth.request.RegisterBody
import uba.fi.goodreads.data.auth.response.LoginResponse
import uba.fi.goodreads.data.auth.response.RegisterResponse
import uba.fi.goodreads.data.shelf.response.ShelfNetworkDto

internal interface BetterReadsClient {

    @POST("/login")
    suspend fun logIn(@Body body: LoginBody): LoginResponse

    @POST("/register")
    suspend fun register(@Body body: RegisterBody): RegisterResponse

    @POST("/shelves")
    suspend fun createShelf(@Body body: CreateShelfBody): ShelfNetworkDto
}