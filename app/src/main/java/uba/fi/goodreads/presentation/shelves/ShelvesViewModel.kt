package uba.fi.goodreads.presentation.shelves

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetShelvesUseCase
import uba.fi.goodreads.presentation.shelves.ShelvesUiState
import javax.inject.Inject

@HiltViewModel
class ShelvesViewModel @Inject constructor(
    private val getShelves: GetShelvesUseCase,
): ViewModel() {

    private val _screenState: MutableStateFlow<ShelvesUiState> =
        MutableStateFlow(ShelvesUiState.Loading)
    val screenState: StateFlow<ShelvesUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            val shelves = GetShelvesUseCase()
            _screenState.update {
                ShelvesUiState.Success(
                    shelves = getShelves()
                )
            }
        }
    }
}