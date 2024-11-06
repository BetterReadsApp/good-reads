package uba.fi.goodreads.presentation.shelves.addBookScreen

import uba.fi.goodreads.domain.mocks.BookMock
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Shelf

data class AddBookToShelvesUiState (
    val book: Book = BookMock.getbook(),
    val shelves: List<Shelf> = emptyList()
)
