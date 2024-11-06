package uba.fi.goodreads.presentation.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.search.SearchScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.search.SearchUiState
import uba.fi.goodreads.presentation.search.SearchViewModel
import uba.fi.goodreads.presentation.search.navigation.SearchDestination

@Composable
fun SearchRoute(
    navigate: (SearchDestination) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    SearchScreen(
        screenState = screenState,
        onSearchChange = viewModel::onSearchChange,
        onBookClicked = viewModel::onBookClick
    )
}

@Composable
fun SearchScreen(
    screenState: SearchUiState,
    onSearchChange: (String) -> Unit,
    onBookClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(32.dp))

        TextField(
            value = screenState.search,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search") }
        )

    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewParameterProvider::class) state: SearchUiState
) {
    GoodReadsTheme {
        SearchScreen(
            state,
            onBookClicked = {},
            onSearchChange = {}
        )
    }
}