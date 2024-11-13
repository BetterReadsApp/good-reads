package uba.fi.goodreads.presentation.shelves.shelf_books

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.shelves.shelf_books.navigation.ShelfBooksDestination

data class ShelfBooksUiState (
    val destination: ShelfBooksDestination? = null,
    val name: String = "",
    val id: Int = 0,
    val books: List<Book> = emptyList()
)
