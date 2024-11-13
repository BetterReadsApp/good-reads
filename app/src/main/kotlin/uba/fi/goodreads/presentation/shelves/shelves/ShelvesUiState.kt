package uba.fi.goodreads.presentation.shelves.shelves

import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.shelves.shelves.navigation.ShelvesDestination

sealed  class ShelvesUiState {
    data object Loading : ShelvesUiState()

    data object Error : ShelvesUiState()

    data class Success(
        val destination: ShelvesDestination? = null,
        val shelves: List<Shelf>,
        val showCreateShelfDialog: Boolean = false,
        val newShelfName: String = ""
    ) : ShelvesUiState()
}
