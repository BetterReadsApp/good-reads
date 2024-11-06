package uba.fi.goodreads.presentation.shelves.shelfBooksScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetBookInfoUseCase
import uba.fi.goodreads.domain.usecase.GetShelfBooksUseCase
import javax.inject.Inject

@HiltViewModel
class ShelfBooksViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBookInfoUseCase: GetBookInfoUseCase,
    private val getShelfBooksUseCase: GetShelfBooksUseCase
): ViewModel() {

    private val _screenState: MutableStateFlow<ShelfBooksUiState> =
        MutableStateFlow(ShelfBooksUiState())
    val screenState: StateFlow<ShelfBooksUiState> = _screenState.asStateFlow()

    private val shelfId: String = savedStateHandle["shelfId"] ?: ""

    init {
        viewModelScope.launch {
            getShelfBooksUseCase(shelfId).also { result ->
                when (result) {
                    is GetShelfBooksUseCase.Result.Error,
                    is GetShelfBooksUseCase.Result.UnexpectedError -> Unit

                    is GetShelfBooksUseCase.Result.Success -> _screenState.update {
                        ShelfBooksUiState(
                            name = result.shelf.name,
                            id = result.shelf.id,
                            books = result.shelf.books
                        )
                    }
                }
            }
        }
    }

    fun onBookClick (bookId: String){
        Unit
    } //TODO


}