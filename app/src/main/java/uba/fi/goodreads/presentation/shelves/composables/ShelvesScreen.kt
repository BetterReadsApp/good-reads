package uba.fi.goodreads.presentation.shelves.composables

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.component.feedback.FeedbackScreen
import uba.fi.goodreads.core.design_system.component.feedback.FeedbackType
import uba.fi.goodreads.core.design_system.component.loading.Loading
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.shelves.ShelvesScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.shelves.ShelvesUiState
import uba.fi.goodreads.presentation.shelves.ShelvesViewModel

@Composable
fun ShelvesRoute(
    viewModel: ShelvesViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    ShelvesScreen(
        screenState = screenState
    )
}

@Composable
fun ShelvesScreen(screenState: ShelvesUiState) {
    when (screenState) {
        ShelvesUiState.Error -> FeedbackScreen(type = FeedbackType.ERROR)
        ShelvesUiState.Loading -> Loading()
        is ShelvesUiState.Success -> SuccessContent(screenState)
    }
}

@Composable
private fun SuccessContent(screenState: ShelvesUiState.Success) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.my_books_bottom_nav_title)
        )
        screenState.shelves.forEach { shelf ->
            Shelf(
                title = shelf.title,
                numberOfBooks = shelf.numberOfBooks,
                books = shelf.books,
                dateAdded = shelf.dateAdded
            )
            Spacer(Modifier.height(16.dp))
        }

    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(
    @PreviewParameter(ShelvesScreenPreviewParameterProvider::class) state: ShelvesUiState
) {
    GoodReadsTheme {
        ShelvesScreen(state)
    }
}