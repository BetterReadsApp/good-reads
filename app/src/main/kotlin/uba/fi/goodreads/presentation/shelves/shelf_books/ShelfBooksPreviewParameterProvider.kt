package uba.fi.goodreads.presentation.shelves.shelf_books

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks

class ShelfBooksPreviewParameterProvider: PreviewParameterProvider<ShelfBooksUiState>{
    override val values: Sequence<ShelfBooksUiState> = sequenceOf(
        getSuccessState()
    )

    private fun getSuccessState() = ShelfBooksUiState(
        books = DomainBookMocks.getBooks(),
    )
}
