package uba.fi.goodreads.presentation.add_book

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
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
            it.copy(coverUrl = value)
        }
    }

    fun onDescriptionChange(value: String) {
        _screenState.update {
            it.copy(coverUrl = value)
        }
    }

    fun onSave() {
        _screenState.update { it.copy(AddBookDestination.Back) }
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

}

