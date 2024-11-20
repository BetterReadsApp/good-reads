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
import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination
import uba.fi.goodreads.presentation.review.BookReviewUIState
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
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
                        )
                    }
                }
            }
        }
    }


    fun onBack() {
        _screenState.update {
            it.copy(destination = AddBookDestination.Back)
        }
    }

    fun onClearDestination() {
        _screenState.update {
            it.copy(destination = null)
        }
    }

    fun onSaveBookClick() {
        viewModelScope.launch {
            saveBookUseCase(title.value, description.value, coverUrl.value).also { result ->
                when (result) {
                    is SaveBookUseCase.Result.Error,
                    is SaveBookUseCase.Result.UnexpectedError -> Unit
                    is SaveBookUseCase.Result.Success -> _screenState.update {
                        it.copy(destination = AddBookDestination.Back)
                    }
                }
            }
        }
    }


}

