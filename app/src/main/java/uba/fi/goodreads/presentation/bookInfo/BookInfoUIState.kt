package uba.fi.goodreads.presentation.bookInfo

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.UserReview
import uba.fi.goodreads.presentation.bookInfo.navigation.BookInfoDestination

data class BookInfoUIState(
    val book: Book = Book(
        author = "",
        description = "",
        genres = emptyList(),
        publicationDate = "",
        title = "",
        avgRating = null,
    ),
    val reviews: List<UserReview> = emptyList(),
    val userRating: Int = 0,
    val destination: BookInfoDestination? = null,
)
