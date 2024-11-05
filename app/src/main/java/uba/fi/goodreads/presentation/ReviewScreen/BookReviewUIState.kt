package uba.fi.goodreads.presentation.ReviewScreen

import uba.fi.goodreads.domain.model.Book

data class BookReviewUIState(
    val book: Book = Book(
        author = "",
        description = "",
        genres = emptyList(),
        publicationDate = "",
        title = "",
        avgRating = null,
    ),
    val reviews: List<String> = emptyList(),
    val userRating: Int = 0,
    val userReview: String? = "",
)
