package uba.fi.goodreads.data.core

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uba.fi.goodreads.data.shelf.request.CreateShelfBody
import uba.fi.goodreads.data.auth.request.LoginBody
import uba.fi.goodreads.data.auth.request.RegisterBody
import uba.fi.goodreads.data.auth.response.LoginResponse
import uba.fi.goodreads.data.auth.response.RegisterResponse
import uba.fi.goodreads.data.books.response.BookNetworkDto
import uba.fi.goodreads.data.shelf.response.ShelfNetworkDto
import uba.fi.goodreads.data.users.response.UserNetworkDto

internal interface BetterReadsClient {

    @POST("/login")
    suspend fun logIn(@Body body: LoginBody): LoginResponse

    @POST("/register")
    suspend fun register(@Body body: RegisterBody): RegisterResponse

    @POST("/shelves")
    suspend fun createShelf(@Body body: CreateShelfBody): ShelfNetworkDto

    @GET("/books/{bookId}")
    suspend fun getBook(@Path("bookId") bookId: String): BookNetworkDto

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): UserNetworkDto
}