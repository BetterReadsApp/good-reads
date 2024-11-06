package uba.fi.goodreads.presentation.search

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainPostMocks

class SearchScreenPreviewParameterProvider: PreviewParameterProvider<SearchUiState> {

    override val values: Sequence<SearchUiState> = sequenceOf(
        getSuccessState(),
    )

    private fun getSuccessState() = SearchUiState(

    )
}