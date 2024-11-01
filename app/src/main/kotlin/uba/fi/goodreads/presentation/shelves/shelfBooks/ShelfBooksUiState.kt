package uba.fi.goodreads.presentation.shelves.shelfBooks

import uba.fi.goodreads.domain.model.Book

sealed  class ShelfBooksUiState {
    data object Loading : ShelfBooksUiState()

    data object Error : ShelfBooksUiState()

    data class Success(
        val books: List<Book>,
    ) : ShelfBooksUiState()
}
