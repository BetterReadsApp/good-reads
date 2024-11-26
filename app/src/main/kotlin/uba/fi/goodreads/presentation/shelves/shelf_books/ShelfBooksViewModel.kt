package uba.fi.goodreads.presentation.shelves.shelf_books

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetShelfBooksUseCase
import uba.fi.goodreads.domain.usecase.RenameShelfUseCase
import uba.fi.goodreads.domain.usecase.DeleteShelfUseCase
import uba.fi.goodreads.presentation.shelves.shelf_books.navigation.ShelfBooksDestination
import javax.inject.Inject

@HiltViewModel
class ShelfBooksViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getShelfBooksUseCase: GetShelfBooksUseCase,
    private val deleteShelfUseCase: DeleteShelfUseCase,
    private val renameShelfUseCase: RenameShelfUseCase
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
        _screenState.update { it.copy(destination = ShelfBooksDestination.BookInfo(bookId)) }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

    fun onRenameShelf(newName: String) {
        viewModelScope.launch{
            renameShelfUseCase(shelfId = shelfId , name = newName).also { result ->
                when (result) {
                    is RenameShelfUseCase.Result.Error,
                    is RenameShelfUseCase.Result.UnexpectedError -> Unit

                    is RenameShelfUseCase.Result.Success -> _screenState.update {
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


    fun onDeleteShelf() {
        viewModelScope.launch{
            deleteShelfUseCase(shelfId = shelfId).also { result ->
                when (result) {
                    is DeleteShelfUseCase.Result.Error,
                    is DeleteShelfUseCase.Result.UnexpectedError -> Unit
                    is DeleteShelfUseCase.Result.Success -> _screenState.update {
                        it.copy(destination = ShelfBooksDestination.Back)
                    }
                }
            }
        }
    }
}


