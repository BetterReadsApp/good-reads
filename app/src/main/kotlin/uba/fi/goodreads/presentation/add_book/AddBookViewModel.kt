package uba.fi.goodreads.presentation.add_book

import androidx.compose.runtime.mutableStateOf
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
class AddBookViewModel @Inject constructor(
    private val reviewBookUseCase: ReviewBookUseCase,
    private val getBookInfoUseCase: GetBookInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState: MutableStateFlow<AddBookUIState> =
        MutableStateFlow(AddBookUIState())
    val screenState: StateFlow<AddBookUIState> = _screenState.asStateFlow()
    private val bookId: String = savedStateHandle["bookId"] ?: ""
    var coverUrl = mutableStateOf("")
    var title = mutableStateOf("")
    var description = mutableStateOf("")

    init {
        viewModelScope.launch {
            getBookInfoUseCase(bookId).also { result ->
                when (result) {
                    is GetBookInfoUseCase.Result.Error,
                    is GetBookInfoUseCase.Result.UnexpectedError -> Unit

                    is GetBookInfoUseCase.Result.Success -> _screenState.update {
                        AddBookUIState(
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
                        AddBookUIState(
                            book = it.book.copy(yourReview = result.newReview
                        ))
                    }
                }
        }
        }}
/*
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
*/
}

