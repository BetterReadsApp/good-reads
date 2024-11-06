package uba.fi.goodreads.domain.model

data class Book(
    val title: String,
    val description: String,
    val author: String,
    val genres: List<String>,
    val publicationDate: String,
    val avgRating: Double? = null,
    val your_rating: Int? = null,
    val your_review: String? = null,
    val reviews: List<UserReview> = emptyList(),
)