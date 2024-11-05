package uba.fi.goodreads.presentation.shelves.shelfBooks

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.BookMock
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainShelfMocks
import uba.fi.goodreads.presentation.bookInfo.BookInfoUIState

class ShelfBooksPreviewParameterProvider: PreviewParameterProvider<ShelfBooksUiState>{
    override val values: Sequence<ShelfBooksUiState> = sequenceOf(
        getSuccessState()
    )

    private fun getSuccessState() = ShelfBooksUiState(
        books = DomainBookMocks.getBooks(),
    )
}
