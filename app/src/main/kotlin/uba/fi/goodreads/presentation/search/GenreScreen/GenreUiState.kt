package uba.fi.goodreads.presentation.search.GenreScreen

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.search.searchScreen.navigation.GenreDestination

data class GenreUiState(
    val destination: GenreDestination? = null,
    val genreName: String = "",
    val books: List<Book> = emptyList()
)