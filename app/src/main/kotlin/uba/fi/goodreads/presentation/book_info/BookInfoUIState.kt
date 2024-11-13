package uba.fi.goodreads.presentation.book_info

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.book_info.navigation.BookInfoDestination

data class BookInfoUIState(
    val book: Book = Book(
        id = "1",
        author = "",
        description = "",
        pages = 0,
        genres = emptyList(),
        publicationDate = "",
        title = "",
        avgRating = null,
        userRated = null,
        hasQuizzes = false,
        photoUrl = ""
    ),
    val userRating: Int = 0,
    val destination: BookInfoDestination? = null,
)
