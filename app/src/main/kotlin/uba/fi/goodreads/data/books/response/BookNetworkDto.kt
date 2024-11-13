package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.data.books.request.QuizDto
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.BookGenre

@Serializable
data class BookNetworkDto(
    @SerialName("pages") val pages: Int? = null,
    @SerialName("title") val title: String,
    @SerialName("id") val id: Int,
    @SerialName("summary") val summary: String? = null,
    @SerialName("author") val author: AuthorDto,
    @SerialName("genre") val genre: String? = null,
    @SerialName("cover_image_url") val photoUrl: String? = null,
    @SerialName("publication_date") val publicationDate: String? = null,
    @SerialName("average_rating") val avgRating: Double? = null,
    @SerialName("your_rating") val yourRating: Int? = null,
    @SerialName("your_review") val yourReview: String? = null,
    @SerialName("reviews") val reviews: List<ReviewNetworkDto> = emptyList(),
    @SerialName("has_quizzes") val hasQuizzes: Boolean? = null,
    @SerialName("quizzes") val quizzes: List<QuizDto>? = null,
) {
    fun toDomain(currentUserId: String? = null) = Book(
        id = id.toString(),
        iAmTheAuthor = author.id?.let { it.toString() == currentUserId } ?: false ,
        title = title,
        author = "${author.name} ${author.lastName}",
        pages = pages ?: 0,
        description = summary ?: "",
        genres = BookGenre.entries.firstOrNull { it.genreName == genre }?.let {
            listOf(it)
        },
        photoUrl = photoUrl ?: "",
        publicationDate = publicationDate ?: "",
        avgRating = avgRating,
        userRated = null,
        yourRating = yourRating,
        yourReview = yourReview,
        reviews = reviews.map { it.toDomain() },
        hasQuizzes = hasQuizzes ?: false,
        quizId = quizzes?.firstOrNull()?.id?.toString()
    )
}

@Serializable
data class RatedBookDto(
    @SerialName("book_id") val id: Int,
    @SerialName("value") val rating: Int ? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("author") val author: AuthorDto? = null,
    @SerialName("cover_image_url") val photoUrl: String? = null,
) {
    fun toDomain() = Book(
        id = id.toString(),
        title = title ?: "",
        author = author?.let { "${it.name} ${it.lastName}" } ?: "",
        userRated = rating,
        photoUrl = photoUrl ?: "",
        pages = 0,
        hasQuizzes = false
    )
}

@Serializable
data class RecommendedBookDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("genre") val genre: String,
    @SerialName("publication_date") val publicationDate: String,
    @SerialName("has_quizzes") val hasQuizzes: Boolean? = null,
    @SerialName("cover_image_url") val photoUrl: String? = null,
) {
    fun toDomain() = Book(
        id = id.toString(),
        title = title,
        author = "Anonimo",
        pages = 0,
        genres = BookGenre.entries.firstOrNull { it.genreName == genre }?.let {
            listOf(it)
        },
        photoUrl = photoUrl ?: "",
        publicationDate = publicationDate,
        hasQuizzes = hasQuizzes ?: false
    )
}
