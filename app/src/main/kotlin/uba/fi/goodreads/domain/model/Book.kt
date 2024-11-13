package uba.fi.goodreads.domain.model

data class Book(
    val id: String,
    val iAmTheAuthor: Boolean? = null,
    val title: String,
    val description: String? = null,
    val author: String,
    val pages: Int,
    val genres: List<BookGenre>? = null,
    val userRated: Int? = null,
    val publicationDate: String? = null,
    val imageUrl: String = "",
    val avgRating: Double? = null,
    val yourRating: Int? = null,
    val your_review: String? = null,
    val reviews: List<UserReview> = emptyList(),
    val hasQuizzes: Boolean
)