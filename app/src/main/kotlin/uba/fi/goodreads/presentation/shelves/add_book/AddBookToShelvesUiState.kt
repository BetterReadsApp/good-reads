package uba.fi.goodreads.presentation.shelves.add_book

import uba.fi.goodreads.domain.mocks.BookMock
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.shelves.add_book.navigation.AddBookToShelfDestination

data class AddBookToShelvesUiState (
    val destination: AddBookToShelfDestination? = null,
    val book: Book = BookMock.getbook(),
    val shelves: List<Shelf> = emptyList(),
    val selectedShelves: Set<String> = emptySet()

)
