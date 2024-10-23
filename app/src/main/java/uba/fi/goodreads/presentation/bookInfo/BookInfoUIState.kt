package uba.fi.goodreads.presentation.bookInfo

import uba.fi.goodreads.domain.model.Book

data class BookInfoUIState(
    val book: Book = Book(
        author = "",
        description = "",
        genres = emptyList(),
        publicationDate = "",
        title = ""
    ),
    val reviews: List<String> = emptyList()
)

