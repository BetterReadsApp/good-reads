package uba.fi.goodreads.data.core

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import uba.fi.goodreads.data.shelf.request.CreateShelfBody
import uba.fi.goodreads.data.auth.request.LoginBody
import uba.fi.goodreads.data.books.repositories.RateBody
import uba.fi.goodreads.data.auth.request.RegisterBody
import uba.fi.goodreads.data.books.repositories.ReviewBody
import uba.fi.goodreads.data.auth.response.LoginResponse
import uba.fi.goodreads.data.auth.response.RegisterResponse
import uba.fi.goodreads.data.books.request.QuizDto
import uba.fi.goodreads.data.books.response.BookNetworkDto
import uba.fi.goodreads.data.books.response.QuizQuestionDto
import uba.fi.goodreads.data.books.response.RatingResponse
import uba.fi.goodreads.data.shelf.request.AddBookToShelfBody
import uba.fi.goodreads.data.books.response.ReviewResponse
import uba.fi.goodreads.data.shelf.response.ShelfNetworkDto
import uba.fi.goodreads.data.users.response.UserNetworkDto

internal interface BetterReadsClient {

    @POST("/login")
    suspend fun logIn(@Body body: LoginBody): LoginResponse

    @POST("/register")
    suspend fun register(@Body body: RegisterBody): RegisterResponse

    @POST("/shelves")
    suspend fun createShelf(@Body body: CreateShelfBody): ShelfNetworkDto

    @GET("/shelves")
    suspend fun getShelves(@Query("user_id") userId: String): List<ShelfNetworkDto>

    @GET("/shelves/{shelfId}")
    suspend fun getShelf(@Path("shelfId") shelfId: Int): ShelfNetworkDto

    @GET("/books/{bookId}")
    suspend fun getBook(@Path("bookId") bookId: String): BookNetworkDto

    @POST("/books/{book_id}/ratings")
    suspend fun rateBook(@Path("book_id") book_id: String, @Header("auth") auth: String, @Body body: RateBody): RatingResponse //aca no falta el user id?

    @GET("/books")
    suspend fun getBooks(@Query("keywords") text: String): List<BookNetworkDto>

    @POST("/books/{book_id}/reviews")
    suspend fun reviewBook(@Path("book_id") book_id: String, @Header("auth") auth: String, @Body body: ReviewBody): ReviewResponse //aca no falta el user id?

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): UserNetworkDto

    @GET("/users")
    suspend fun searchUsers(@Query("name") text: String): List<UserNetworkDto>

    @POST("/users/{userId}/followers")
    suspend fun followUser(@Path("userId") userId: String)

    @DELETE("/users/{userId}/followers")
    suspend fun unfollowUser(@Path("userId") userId: String)

    @POST("/shelves/{shelfId}/books")
    suspend fun addBookToShelf(@Path("shelfId") shelfId: String, @Body body: AddBookToShelfBody)

    @POST("/books/{bookId}/quiz")
    suspend fun editQuiz(@Path("bookId") bookId: String, @Body body: QuizDto)

    @GET("/books/{bookId}/quiz")
    suspend fun getQuiz(@Path("bookId") bookId: String): List<QuizQuestionDto>

    @GET("/recommended")
    suspend fun getRecommendedBooks(): List<BookNetworkDto>


}