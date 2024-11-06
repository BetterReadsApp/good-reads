package uba.fi.goodreads.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uba.fi.goodreads.domain.usecase.GetFeedUseCase
import uba.fi.goodreads.domain.usecase.GetForYouUseCase
import uba.fi.goodreads.presentation.search.navigation.SearchDestination
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFeed: GetFeedUseCase,
    private val getForYou: GetForYouUseCase,
): ViewModel() {

    private val _screenState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())
    val screenState: StateFlow<SearchUiState> = _screenState.asStateFlow()

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

    fun onBookClick(id: Int) {
        _screenState.update { it.copy(destination = SearchDestination.BookInfo(id)) }
    }

    fun onUserClick(id: Int) {
        _screenState.update { it.copy(destination = SearchDestination.Profile(id)) }
    }

    fun onSearchChange(text: String) {
        _screenState.update { it.copy(search = text) }
    }
}