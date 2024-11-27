package uba.fi.goodreads.presentation.edit_book

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.model.BookToSerialize
import uba.fi.goodreads.domain.usecase.EditBookUseCase
import uba.fi.goodreads.domain.usecase.GetBookInfoUseCase
import uba.fi.goodreads.domain.usecase.SaveBookUseCase
import uba.fi.goodreads.presentation.edit_book.navigation.EditBookDestination
import javax.inject.Inject

@HiltViewModel
class EditBookViewModel @Inject constructor(
    private val getBookInfoUseCase: GetBookInfoUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val editBookUseCase: EditBookUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<EditBookUIState> =
        MutableStateFlow(EditBookUIState())
    val screenState: StateFlow<EditBookUIState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: ""

    init {
        viewModelScope.launch {
            getBookInfoUseCase(bookId).also { result ->
                when (result) {
                    is GetBookInfoUseCase.Result.Error,
                    is GetBookInfoUseCase.Result.UnexpectedError -> _screenState.update { state ->
                        state.copy(
                            loading = false
                        )
                    }
                    is GetBookInfoUseCase.Result.Success -> _screenState.update { state ->
                        val book = result.book
                        state.copy(
                            coverUrl = book.photoUrl,
                            title = book.title,
                            description = book.description?: "",
                            //genre = book.genre,
                            pages = book.pages,
                            loading = false
                        )
                    }
                }
            }
        }
    }


    fun onCoverUrlChange(value: String) {
        _screenState.update {
            it.copy(coverUrl = value)
        }
    }

    fun onTitleChange(value: String) {
        _screenState.update {
            it.copy(title = value)
        }
    }

    fun onDescriptionChange(value: String) {
        _screenState.update {
            it.copy(description = value)
        }
    }

    fun onPagesChange(value: String) {
        _screenState.update {
            it.copy(pages = value.toInt())
        }
    }

    fun onBack() {
        _screenState.update {
            it.copy(destination = EditBookDestination.Back)
        }
    }

    fun onClearDestination() {
        _screenState.update {
            it.copy(destination = null)
        }
    }

    fun onSaveChangesClick() {
        viewModelScope.launch {

            val bookToSerialize = BookToSerialize(
                title = _screenState.value.title,
                summary = _screenState.value.description,
                genre = _screenState.value.genre,
                pages = _screenState.value.pages,
                publicationDate = _screenState.value.publicationDate,
                coverUrl = _screenState.value.coverUrl,
            )
            editBookUseCase(bookId, bookToSerialize).also {
            result ->
                when (result) {
                    is EditBookUseCase.Result.Error,
                    is EditBookUseCase.Result.UnexpectedError -> Unit
                    is EditBookUseCase.Result.Success -> _screenState.update {
                        it.copy(destination = EditBookDestination.Back)
                    }
                }
            }
        }
    }
}

