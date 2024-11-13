package uba.fi.goodreads.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.SearchUseCase
import uba.fi.goodreads.presentation.search.navigation.SearchDestination
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private var searchJob: Job? = null

    private val _screenState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())
    val screenState: StateFlow<SearchUiState> = _screenState.asStateFlow()

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

    fun onBookClick(id: String) {
        _screenState.update { it.copy(destination = SearchDestination.BookInfo(id)) }
    }

    fun onUserClick(id: Int) {
        _screenState.update { it.copy(destination = SearchDestination.Profile(id)) }
    }

    fun onSearchChange(text: String) {
        _screenState.update { it.copy(search = text) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchUseCase(text).also { result ->
                when(result){
                    is SearchUseCase.Result.Error,
                    SearchUseCase.Result.UnexpectedError -> Unit // TODO
                    is SearchUseCase.Result.Success -> _screenState.update {
                        it.copy(books = result.books, users = result.users)
                    }
                }
            }
        }
    }

    fun onGenreClick(s: String) {

    }
}