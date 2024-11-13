package uba.fi.goodreads.domain.model

data class Book(
    val id: String,
    val iAmTheAuthor: Boolean = false,
    val title: String,
    val description: String? = null,
    val author: String,
    val photoUrl: String,
    val pages: Int,
    val genres: List<BookGenre>? = null,
    val userRated: Int? = null,
    val publicationDate: String? = null,
    val avgRating: Double? = null,
    val yourRating: Int? = null,
    val yourReview: String? = null,
    val reviews: List<UserReview> = emptyList(),
    val hasQuizzes: Boolean,
    val quizId: String? = null
)