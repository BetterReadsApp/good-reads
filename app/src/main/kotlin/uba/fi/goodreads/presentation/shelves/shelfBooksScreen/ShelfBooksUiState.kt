package uba.fi.goodreads.presentation.shelves.shelfBooksScreen

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.shelves.shelfBooksScreen.navigation.ShelfBooksDestination

data class ShelfBooksUiState (
    val destination: ShelfBooksDestination? = null,
    val name: String = "",
    val id: Int = 0,
    val books: List<Book> = emptyList()
)
