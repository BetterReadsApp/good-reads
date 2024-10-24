package uba.fi.goodreads.presentation.shelves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.usecase.CreateShelfUseCase
import uba.fi.goodreads.domain.usecase.GetShelvesUseCase
import uba.fi.goodreads.presentation.shelves.ShelvesUiState
import javax.inject.Inject

@HiltViewModel
class ShelvesViewModel @Inject constructor(
    private val getShelves: GetShelvesUseCase,
    private val createShelf: CreateShelfUseCase
): ViewModel() {

    private val _screenState: MutableStateFlow<ShelvesUiState> =
        MutableStateFlow(ShelvesUiState.Loading)
    val screenState: StateFlow<ShelvesUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            _screenState.update {
                ShelvesUiState.Success(
                    shelves = getShelves()
                )
            }
        }
    }

    fun onCreateShelfClick() {
        _screenState.update { state ->
            (state as? ShelvesUiState.Success)?.copy(
                showCreateShelfDialog = true
            ) ?: state
        }
    }

    fun onDismissDialog() {
        _screenState.update { state ->
            (state as? ShelvesUiState.Success)?.copy(
                showCreateShelfDialog = false
            ) ?: state
        }
    }

    fun onShelfNameChange(value: String) {
        _screenState.update { state ->
            (state as? ShelvesUiState.Success)?.copy(
                newShelfName = value
            ) ?: state
        }
    }

    fun onConfirmShelfCreation() {
        viewModelScope.launch {
            createShelf(name = (screenState.value as? ShelvesUiState.Success)?.newShelfName ?: "").also { result ->
                when(result) {
                    is CreateShelfUseCase.Result.Error,
                    is CreateShelfUseCase.Result.UnexpectedError -> Unit // TODO
                    is CreateShelfUseCase.Result.Success -> Unit
                }
                _screenState.update { state ->
                    (state as? ShelvesUiState.Success)?.copy(
                        showCreateShelfDialog = false
                    ) ?: state
                }
            }
        }
    }
}