package uba.fi.goodreads.presentation.bookInfo

import uba.fi.goodreads.domain.model.Book

data class BookInfoUIState(
    val book: Book = Book(
        id = "1",
        author = "",
        description = "",
        genres = emptyList(),
        publicationDate = "",
        title = "",
        avgRating = null,
        userRated = null
    ),
    val reviews: List<String> = emptyList(),
    val userRating: Int = 0
)
