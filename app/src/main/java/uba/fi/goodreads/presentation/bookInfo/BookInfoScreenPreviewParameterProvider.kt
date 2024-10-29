package uba.fi.goodreads.presentation.bookInfo

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.BookMock

class BookInfoScreenPreviewParameterProvider: PreviewParameterProvider<BookInfoUIState> {
    override val values: Sequence<BookInfoUIState> = sequenceOf(
        getSuccessState(),
    )

    private fun getSuccessState() = BookInfoUIState (
        book = BookMock.getbook(),
    )
}
