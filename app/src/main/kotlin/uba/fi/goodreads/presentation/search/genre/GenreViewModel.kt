package uba.fi.goodreads.presentation.search.genre

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetGenreBooksUseCase
import uba.fi.goodreads.presentation.search.search.navigation.GenreDestination
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(

    private val savedStateHandle: SavedStateHandle,
    private val getGenreBooksUseCase: GetGenreBooksUseCase
): ViewModel() {

    private val genreName: String = savedStateHandle["genre"] ?: ""

    private val _screenState: MutableStateFlow<GenreUiState> =
        MutableStateFlow(GenreUiState())
    val screenState: StateFlow<GenreUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            getGenreBooksUseCase(genreName).also { result ->
                when(result) {
                    is GetGenreBooksUseCase.Result.Error,
                    is GetGenreBooksUseCase.Result.UnexpectedError -> Unit
                    is GetGenreBooksUseCase.Result.Success -> _screenState.update {
                        it.copy(books = result.books)
                    }
                }
            }
        }
    }
    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

    fun onBookClick(id: String) {
        _screenState.update { it.copy(destination = GenreDestination.BookInfo(id)) }
    }

}