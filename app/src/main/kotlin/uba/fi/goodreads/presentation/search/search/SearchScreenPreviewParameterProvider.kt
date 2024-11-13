package uba.fi.goodreads.presentation.search.search

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainUsersMock

class SearchScreenPreviewParameterProvider: PreviewParameterProvider<SearchUiState> {

    override val values: Sequence<SearchUiState> = sequenceOf(
        getSuccessState(),
        getBlankState()
    )

    private fun getSuccessState() = SearchUiState(
        search = "Busca",
        books = DomainBookMocks.getBooks(),
        users = DomainUsersMock.getUsers()
    )
    private fun getBlankState() = SearchUiState(
        search = "",
        books = emptyList(),
        users = emptyList()
    )
}