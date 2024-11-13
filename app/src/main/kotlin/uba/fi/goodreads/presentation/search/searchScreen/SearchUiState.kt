package uba.fi.goodreads.presentation.search.searchScreen

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.User
import uba.fi.goodreads.presentation.search.searchScreen.navigation.SearchDestination

data class SearchUiState(
    val destination: SearchDestination? = null,
    val search: String = "",
    val users: List<User> = emptyList(),
    val books: List<Book> = emptyList()
)