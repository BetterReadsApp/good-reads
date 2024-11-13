package uba.fi.goodreads.presentation.shelves.add_book

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.BookMock
import uba.fi.goodreads.domain.mocks.DomainShelfMocks

class AddBookToShelvesParameterProvider: PreviewParameterProvider<AddBookToShelvesUiState>{
    override val values: Sequence<AddBookToShelvesUiState> = sequenceOf(
        getSuccessState()
    )

    private fun getSuccessState() = AddBookToShelvesUiState(
        book = BookMock.getBook(),
        shelves = DomainShelfMocks.getShelves()
    )
}
