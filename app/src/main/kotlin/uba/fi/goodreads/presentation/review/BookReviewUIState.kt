package uba.fi.goodreads.presentation.review

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.review.navigation.ReviewDestination

data class BookReviewUIState(
    val destination: ReviewDestination? = null,
    val book: Book = Book(
        author = "",
        description = "",
        genres = emptyList(),
        publicationDate = "",
        title = "",
        avgRating = null,
        id = "1",
        pages = 1
    ),
    val reviews: List<String> = emptyList(),
    val userRating: Int = 0,
    val userReview: String? = "",
)
