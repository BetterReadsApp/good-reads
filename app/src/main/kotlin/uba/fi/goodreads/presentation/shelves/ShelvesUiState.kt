package uba.fi.goodreads.presentation.shelves

import uba.fi.goodreads.domain.model.Shelf

sealed  class ShelvesUiState {
    data object Loading : ShelvesUiState()

    data object Error : ShelvesUiState()

    data class Success(
        val shelves: List<Shelf>,
        val showCreateShelfDialog: Boolean = false,
        val newShelfName: String = ""
    ) : ShelvesUiState()
}
