package uba.fi.goodreads.presentation.shelves.shelvesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.CreateShelfUseCase
import uba.fi.goodreads.domain.usecase.GetShelfUseCase
import uba.fi.goodreads.domain.usecase.GetShelvesUseCase
import uba.fi.goodreads.presentation.home.navigation.HomeDestination
import javax.inject.Inject

@HiltViewModel
class ShelvesViewModel @Inject constructor(
    private val getShelves: GetShelvesUseCase,
    private val createShelf: CreateShelfUseCase,
    private val getShelf: GetShelfUseCase
): ViewModel() {

    private val _screenState: MutableStateFlow<ShelvesUiState> =
        MutableStateFlow(ShelvesUiState.Loading)
    val screenState: StateFlow<ShelvesUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            _screenState.update {
                when (val result = getShelves()) {
                    is GetShelvesUseCase.Result.Error,
                    is GetShelvesUseCase.Result.UnexpectedError -> ShelvesUiState.Error
                    is GetShelvesUseCase.Result.Success -> ShelvesUiState.Success(
                        shelves = result.shelves
                    )
                }
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

    ///fun onShelfClick(id: Int) {
    //        _screenState.update { it.copy(destination = shelvesDestination.ShelfBooks(id)) }
    //    }
}