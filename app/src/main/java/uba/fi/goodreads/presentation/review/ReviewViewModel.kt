package uba.fi.goodreads.presentation.review

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
import uba.fi.goodreads.presentation.bookInfo.BookInfoUIState
import uba.fi.goodreads.presentation.review.navigation.ReviewDestination
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewBookUseCase: ReviewBookUseCase,
    private val getBookInfoUseCase: GetBookInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState: MutableStateFlow<BookReviewUIState> =
        MutableStateFlow(BookReviewUIState())
    val screenState: StateFlow<BookReviewUIState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: ""

    init {
        viewModelScope.launch {
            getBookInfoUseCase(bookId).also { result ->
                when (result) {
                    is GetBookInfoUseCase.Result.Error,
                    is GetBookInfoUseCase.Result.UnexpectedError -> Unit

                    is GetBookInfoUseCase.Result.Success -> _screenState.update {
                        BookReviewUIState(
                            book = result.book
                        )
                    }
                }
            }
        }
    }

    fun onReviewChange(review: String) {
        viewModelScope.launch {
            reviewBookUseCase(bookId, review).also { result ->
                when (result) {
                    is ReviewBookUseCase.Result.Error,
                    is ReviewBookUseCase.Result.UnexpectedError -> Unit
                    is ReviewBookUseCase.Result.Success -> _screenState.update {
                        BookReviewUIState(
                            book = it.book.copy(your_review = result.newReview
                        ))
                    }
                }
        }
    }}

    fun onBack() {
        _screenState.update {
            it.copy(destination = ReviewDestination.Back)
        }
    }

    fun onClearDestination() {
        _screenState.update {
            it.copy(destination = null)
        }
    }
}
