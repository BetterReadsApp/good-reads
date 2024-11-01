package uba.fi.goodreads.presentation.shelves.shelfBooks

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainShelfMocks

class ShelfBooksPreviewParameterProvider: PreviewParameterProvider<ShelfBooksUiState>{
    override val values: Sequence<ShelfBooksUiState> = sequenceOf(
        getSuccessState(),
        ShelfBooksUiState.Loading,
        ShelfBooksUiState.Error,
    )

    private fun getSuccessState() = ShelfBooksUiState.Success(
        shelves = DomainBookMocks.getBooks(),
    )
}