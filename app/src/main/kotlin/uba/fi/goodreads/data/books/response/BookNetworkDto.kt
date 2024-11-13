package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.BookGenre

@Serializable
data class BookNetworkDto(
    @SerialName("pages") val pages: Int? = null,
    @SerialName("title") val title: String,
    @SerialName("id") val id: Int,
    @SerialName("summary") val summary: String? = null,
    @SerialName("author") val author: AuthorOfRecommended,
    @SerialName("genre") val genre: BookGenre? = null,
    @SerialName("publication_date") val publicationDate: String? = null,
    @SerialName("average_rating") val avgRating: Double? = null,
    @SerialName("your_rating") val yourRating: Int? = null,
    @SerialName("your_review") val yourReview: String? = null,
    @SerialName("reviews") val reviews: List<ReviewNetworkDto> = emptyList(),
) {
    fun toDomain(currentUserId: String? = null) = Book(
        id = id.toString(),
        iAmTheAuthor = authorId?.let { it.toString() == currentUserId },
        title = title,
        author = "${author.name} ${author.lastName}",
        pages = pages ?: 0,
        description = summary ?: "",
        genres = genre?.let { listOf(it) } ?: emptyList(),
        publicationDate = publicationDate ?: "",
        avgRating = avgRating,
        userRated = null,
        your_rating = yourRating,
        your_review = yourReview,
        reviews = reviews.map { it.toDomain() }
    )
}

@Serializable
data class RatedBookDto(
    @SerialName("id") val id: Int,
    @SerialName("value") val rating: Int,
    @SerialName("title") val title: String,
    @SerialName("author") val author: String,
) {
    fun toDomain() = Book(
        id = id.toString(),
        title = title,
        author = author,
        userRated = rating,
        pages = 0
    )
}

@Serializable
data class RecommendedBookDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("genre") val genre: String,
    @SerialName("publication_date") val publicationDate: String,
    @SerialName("has_quizzes") val hasQuizzes: Boolean,
) {
    fun toDomain() = Book(
        id = id.toString(),
        title = title,
        author = "Anonimo",
        pages = 0,
        genres = listOf(genre),
        publicationDate = publicationDate,
    )
}
