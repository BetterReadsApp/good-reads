package uba.fi.goodreads.presentation.shelves.add_book

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.AddBookToShelvesUseCase
import uba.fi.goodreads.domain.usecase.GetBookInfoUseCase
import uba.fi.goodreads.domain.usecase.GetShelvesUseCase
import uba.fi.goodreads.presentation.shelves.add_book.navigation.AddBookToShelfDestination
import javax.inject.Inject

@HiltViewModel
class AddBookToShelvesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val addBookToShelvesUseCase: AddBookToShelvesUseCase,
    private val getShelvesUseCase: GetShelvesUseCase,
    private val getBookInfoUseCase: GetBookInfoUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<AddBookToShelvesUiState> =
        MutableStateFlow(AddBookToShelvesUiState())
    val screenState: StateFlow<AddBookToShelvesUiState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: "0"

    init {
        viewModelScope.launch {
            getShelvesUseCase().also { result ->
                when(result) {
                    is GetShelvesUseCase.Result.Error,
                    is GetShelvesUseCase.Result.UnexpectedError -> Unit
                    is GetShelvesUseCase.Result.Success -> _screenState.update {
                        it.copy(shelves = result.shelves)
                    }
                }
            }

            getBookInfoUseCase(bookId).also { result ->
                when(result) {
                    is GetBookInfoUseCase.Result.Error -> TODO()
                    is GetBookInfoUseCase.Result.Success -> _screenState.update {
                        it.copy(book = result.book)
                    }
                    GetBookInfoUseCase.Result.UnexpectedError -> TODO()
                }
            }
        }
    }

    fun onClearDestination() {
        _screenState.update {
            it.copy(destination = null)
        }
    }

    fun onConfirm() {
        viewModelScope.launch {
            addBookToShelvesUseCase(bookId, _screenState.value.selectedShelves).also { result ->
                when (result) {
                    is AddBookToShelvesUseCase.Result.Error,
                    is AddBookToShelvesUseCase.Result.UnexpectedError -> Unit
                    is AddBookToShelvesUseCase.Result.Success -> _screenState.update {
                        it.copy(destination = AddBookToShelfDestination.Back)
                    }
                }
            }
        }
    }

    fun onShelfClick(shelfId: String, isSelected: Boolean) {
        _screenState.update {
            it.copy(
                selectedShelves = if (isSelected) {
                    it.selectedShelves + shelfId
                } else {
                    it.selectedShelves - shelfId
                }
            )
        }
    }

}