package uba.fi.goodreads.presentation.home

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Post

data class HomeUiState(
    val feed: List<Post> = emptyList(),
    val forYou: List<Book> = emptyList(),
)