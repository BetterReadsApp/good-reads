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
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.usecase.UpdateBookOnShelvesUseCase
import uba.fi.goodreads.domain.usecase.GetBookInfoUseCase
import uba.fi.goodreads.domain.usecase.GetShelvesUseCase
import uba.fi.goodreads.presentation.shelves.add_book.navigation.AddBookToShelfDestination
import javax.inject.Inject

@HiltViewModel
class AddBookToShelvesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val updateBookOnShelvesUseCase: UpdateBookOnShelvesUseCase,
    private val getShelvesUseCase: GetShelvesUseCase,
    private val getBookInfoUseCase: GetBookInfoUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<AddBookToShelvesUiState> =
        MutableStateFlow(AddBookToShelvesUiState())
    val screenState: StateFlow<AddBookToShelvesUiState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: "0"

    private val shelvesContainingBook: MutableList<String> = emptyList<String>().toMutableList()

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
                    is GetBookInfoUseCase.Result.Error,
                    is GetBookInfoUseCase.Result.UnexpectedError -> Unit
                    is GetBookInfoUseCase.Result.Success -> _screenState.update {
                        it.copy(book = result.book)
                    }
                }
            }

            _screenState.value.shelves.forEach { shelf ->
                if (shelf.containsBook(_screenState.value.book.title)){
                    shelvesContainingBook.add(shelf.id.toString())
                }
            }
            _screenState.update {
                it.copy(selectedShelves = shelvesContainingBook.toSet())
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
            updateBookOnShelvesUseCase(
                bookId,
                _screenState.value.selectedShelves,
                _screenState.value.shelves.filter { shelf ->
                    !_screenState.value.selectedShelves.contains(shelf.id.toString())
                }.filter { notSelectedShelf ->
                    shelvesContainingBook.contains(notSelectedShelf.id.toString())
                }.map {
                    it.id.toString()
                }.toSet()
            ).also { result ->
                when (result) {
                    is UpdateBookOnShelvesUseCase.Result.Error,
                    is UpdateBookOnShelvesUseCase.Result.UnexpectedError -> Unit
                    is UpdateBookOnShelvesUseCase.Result.Success -> _screenState.update {
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