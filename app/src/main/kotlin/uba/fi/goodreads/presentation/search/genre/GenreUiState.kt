package uba.fi.goodreads.presentation.search.genre

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.search.search.navigation.GenreDestination

data class GenreUiState(
    val destination: GenreDestination? = null,
    val genreName: String = "",
    val books: List<Book> = emptyList()
)