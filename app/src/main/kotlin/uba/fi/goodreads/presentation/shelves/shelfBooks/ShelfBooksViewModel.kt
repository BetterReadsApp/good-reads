package uba.fi.goodreads.presentation.shelves.shelfBooks

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
import uba.fi.goodreads.presentation.shelves.shelvesScreen.ShelvesUiState
import javax.inject.Inject

@HiltViewModel
class ShelfBooksViewModel @Inject constructor(
    private val getBookInfoUseCase: GetBookInfoUseCase,
    private val getShelfBooksUseCase: GetShelfBooksUseCase
): ViewModel() {

    private val _screenState: MutableStateFlow<ShelfBooksUiState> =
        MutableStateFlow(ShelfBooksUiState.Loading)
    val screenState: StateFlow<ShelfBooksUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            _screenState.update {
                when (val result = getShelfBooksUseCase()) {
                    is GetShelfBooksUseCase.Result.Error,
                    is GetShelfBooksUseCase.Result.UnexpectedError -> ShelfBooksUiState.Error
                    is GetShelfBooksUseCase.Result.Success -> ShelfBooksUiState.Success(
                        result.books
                    )
                }
            }
        }
    }



}