package uba.fi.goodreads.presentation.bookInfo

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
import uba.fi.goodreads.domain.usecase.ReviewBookUseCase
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBookInfoUseCase: GetBookInfoUseCase,
    private val reviewBookUseCase: ReviewBookUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<BookInfoUIState> =
        MutableStateFlow(BookInfoUIState())
    val screenState: StateFlow<BookInfoUIState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: ""

    init {
        viewModelScope.launch {
            getBookInfoUseCase(bookId).also { result ->
                when (result) {
                    is GetBookInfoUseCase.Result.Error,
                    is GetBookInfoUseCase.Result.UnexpectedError -> Unit

                    is GetBookInfoUseCase.Result.Success -> _screenState.update {
                        BookInfoUIState(
                            book = result.book
                        )
                    }
                }
            }
        }
    }

    fun onUserRatingChange(rating: Int) {
        viewModelScope.launch {
            reviewBookUseCase(bookId, rating).also { result ->
                when (result) {
                    is ReviewBookUseCase.Result.Error,
                    is ReviewBookUseCase.Result.UnexpectedError -> Unit

                    is ReviewBookUseCase.Result.Success -> _screenState.update { state ->
                        state.copy(
                            userRating = rating,
                            book = state.book.copy(avgRating = result.avgRating)
                        )
                    }
                }
            }
        }
    }
}
