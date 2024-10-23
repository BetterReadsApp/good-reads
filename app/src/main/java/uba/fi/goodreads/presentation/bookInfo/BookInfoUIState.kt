package uba.fi.goodreads.presentation.bookInfo

import uba.fi.goodreads.domain.mocks.BookMock
import uba.fi.goodreads.domain.model.Book

data class BookInfoUIState(
    val book: Book = BookMock.getbook(),
    val reviews: List<String> = emptyList()
)

