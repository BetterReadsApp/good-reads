package uba.fi.goodreads.presentation.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainPostMocks

class HomeScreenPreviewParameterProvider: PreviewParameterProvider<HomeUiState> {

    override val values: Sequence<HomeUiState> = sequenceOf(
        getSuccessState(),
    )

    private fun getSuccessState() = HomeUiState(
        feed = DomainPostMocks.getPosts(),
        forYou = DomainBookMocks.getBooks()
    )
}