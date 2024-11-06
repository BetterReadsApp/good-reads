package uba.fi.goodreads.presentation.shelves.addBookScreen

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
import uba.fi.goodreads.domain.usecase.addBookToShelvesUseCase
import javax.inject.Inject

@HiltViewModel
class AddBookToShelvesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val addBookToShelvesUseCase: addBookToShelvesUseCase
): ViewModel() {

    private val _screenState: MutableStateFlow<AddBookToShelvesUiState> =
        MutableStateFlow(AddBookToShelvesUiState())
    val screenState: StateFlow<AddBookToShelvesUiState> = _screenState.asStateFlow()

    private val shelfId: String = savedStateHandle["bookId"] ?: "0"

    init {
        viewModelScope.launch {
            addBookToShelvesUseCase(shelfId).also { result ->
                when (result) {
                    is addBookToShelvesUseCase.Result.Success -> _screenState.update {
                        AddBookToShelvesUiState(
                            book = result.book,
                            shelves = result.shelves
                        )
                    }

                    is addBookToShelvesUseCase.Result.Error -> TODO()
                    is addBookToShelvesUseCase.Result.UnexpectedError -> TODO()
                }
            }
        }
    }



}