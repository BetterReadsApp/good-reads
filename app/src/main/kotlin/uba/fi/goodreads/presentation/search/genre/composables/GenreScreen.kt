package uba.fi.goodreads.presentation.search.genre.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.core.design_system.component.book_row.BookRow
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.presentation.search.genre.GenrePreviewParameterProvider
import uba.fi.goodreads.presentation.search.genre.GenreUiState
import uba.fi.goodreads.presentation.search.genre.GenreViewModel
import uba.fi.goodreads.presentation.search.search.navigation.GenreDestination

@Composable
fun GenreRoute(
    navigate: (GenreDestination) -> Unit,
    viewModel: GenreViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    GenreScreen(
        screenState = screenState,
        onBookClick = viewModel::onBookClick
    )
}

@Composable
fun GenreScreen(
    screenState: GenreUiState,
    onBookClick: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            modifier = Modifier.padding(vertical = 32.dp),
            text = screenState.genreName,
            fontSize = 32.sp
        )
        screenState.books.forEach { book ->
            BookRow(book,modifier = Modifier.fillMaxWidth(),
                onClick = { onBookClick(book.id) })
            Spacer(Modifier.height(16.dp))
        }
        Spacer(Modifier.weight(1f))
    }

}


@Composable
@Preview(showBackground = true)
fun GenreScreenPreview(
    @PreviewParameter(GenrePreviewParameterProvider::class) state: GenreUiState
) {
    GoodReadsTheme {
        GenreScreen(
            state,
            onBookClick = {}
        )
    }
}