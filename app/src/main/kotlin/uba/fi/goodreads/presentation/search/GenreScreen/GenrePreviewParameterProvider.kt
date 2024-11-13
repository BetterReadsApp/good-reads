package uba.fi.goodreads.presentation.search.GenreScreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainUsersMock

class GenrePreviewParameterProvider: PreviewParameterProvider<GenreUiState> {

    override val values: Sequence<GenreUiState> = sequenceOf(
        getSuccessState()
    )

    private fun getSuccessState() = GenreUiState(
        books = DomainBookMocks.getBooks()
    )
}