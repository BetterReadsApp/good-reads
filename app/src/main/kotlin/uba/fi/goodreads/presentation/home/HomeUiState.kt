package uba.fi.goodreads.presentation.home

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Post
import uba.fi.goodreads.presentation.home.navigation.HomeDestination
import uba.fi.goodreads.presentation.search.navigation.SearchDestination

data class HomeUiState(
    val destination: HomeDestination? = null,
    val feed: List<Post> = emptyList(),
    val forYou: List<Book> = emptyList(),
)