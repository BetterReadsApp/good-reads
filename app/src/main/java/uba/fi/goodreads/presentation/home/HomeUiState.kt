package uba.fi.goodreads.presentation.home

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Post

sealed class HomeUiState {
    data object Loading : HomeUiState()

    data object Error : HomeUiState()

    data class Success(
        val feed: List<Post>,
        val forYou: List<Book>
    ) : HomeUiState()
}