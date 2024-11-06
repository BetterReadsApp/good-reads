package uba.fi.goodreads.presentation.book_info

import uba.fi.goodreads.domain.model.Book

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
        userRated = null
    ),
    val reviews: List<String> = emptyList(),
    val userRating: Int = 0
)
