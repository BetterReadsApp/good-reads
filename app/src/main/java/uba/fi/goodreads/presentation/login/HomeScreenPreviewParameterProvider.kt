package uba.fi.goodreads.presentation.login

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainPostMocks

class HomeScreenPreviewParameterProvider: PreviewParameterProvider<HomeUiState> {

    override val values: Sequence<HomeUiState> = sequenceOf(
        getSuccessState(),
        HomeUiState.Loading,
        HomeUiState.Error,
    )

    private fun getSuccessState() = HomeUiState.Success(
        feed = DomainPostMocks.getPosts(),
        forYou = DomainBookMocks.getBooks()
    )
}