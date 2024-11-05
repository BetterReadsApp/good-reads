package uba.fi.goodreads.presentation.shelves.shelfBooks

import uba.fi.goodreads.domain.model.Book

data class ShelfBooksUiState (
    val name: String = "",
    val id: Int = 0,
    val books: List<Book> = emptyList()
)
