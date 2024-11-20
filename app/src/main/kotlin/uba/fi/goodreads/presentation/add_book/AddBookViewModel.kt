package uba.fi.goodreads.presentation.add_book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.model.AddedBook
import uba.fi.goodreads.domain.usecase.SaveBookUseCase
import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val saveBookUseCase: SaveBookUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<AddBookUIState> =
        MutableStateFlow(AddBookUIState())
    val screenState: StateFlow<AddBookUIState> = _screenState.asStateFlow()

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

            val addedBook = AddedBook(
                title = _screenState.value.title,
                summary = _screenState.value.description,
                genre = _screenState.value.genre,
                pages = _screenState.value.pages,
                publicationDate = _screenState.value.publicationDate,
                coverUrl = _screenState.value.coverUrl,
            )
            saveBookUseCase(addedBook).also {
            result ->
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

