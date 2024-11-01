package uba.fi.goodreads.presentation.shelves.shelvesScreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainShelfMocks

class ShelvesScreenPreviewParameterProvider: PreviewParameterProvider<ShelvesUiState>{
    override val values: Sequence<ShelvesUiState> = sequenceOf(
        getSuccessState(),
        ShelvesUiState.Loading,
        ShelvesUiState.Error,
    )

    private fun getSuccessState() = ShelvesUiState.Success(
        shelves = DomainShelfMocks.getShelves(),
    )
}