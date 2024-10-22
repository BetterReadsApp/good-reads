package uba.fi.goodreads.presentation.shelves.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
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
        screenState = screenState,
        onCreateShelfClick = viewModel::onCreateShelfClick
    )
}

@Composable
fun ShelvesScreen(
    screenState: ShelvesUiState,
    onCreateShelfClick: () -> Unit
) {
    when (screenState) {
        ShelvesUiState.Error -> FeedbackScreen(type = FeedbackType.ERROR)
        ShelvesUiState.Loading -> Loading()
        is ShelvesUiState.Success -> SuccessContent(screenState,onCreateShelfClick)
    }
}

@Composable
private fun SuccessContent(screenState: ShelvesUiState.Success,
onCreateShelfClick: () -> Unit) {
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
        Spacer(Modifier.height(32.dp))
        Text(
            text = "Shelves"
        )
        Spacer(Modifier.height(16.dp))
        screenState.shelves.forEach { shelf ->
            ShelfPreview(shelf)
            Spacer(Modifier.height(16.dp))
        }
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.
            fillMaxWidth().
            padding(
                vertical = 24.dp,
                horizontal = 18.dp
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = onCreateShelfClick
        ) {
            Text(text = "create shelf")
        }

    }
}

@Composable
private fun ShelfPreview(shelf: Shelf) {
    Card (modifier = Modifier.fillMaxWidth()){
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://f.media-amazon.com/images/I/41tjPqycZ1L._SY445_SX342_.jpg") // TODO AL MODEL
                    .crossfade(true)
                    .build(),
                // placeholder = painterResource(R.drawable.placeholder),
                contentDescription = "Cover of one of the books present inside the shelf",
                contentScale = ContentScale.Crop,
            )
            Column{
                Text(
                    text = shelf.title
                )
                Text (
                    text = shelf.numberOfBooks.toString() + " books"
                )
                Text(
                    text = "created at: " + shelf.dateAdded.toString()
                )
            }


        }
    }


}

@Composable
@Preview(showBackground = true)
fun ShelvesScreenPreview(
    @PreviewParameter(ShelvesScreenPreviewParameterProvider::class) state: ShelvesUiState
) {
    GoodReadsTheme {
        ShelvesScreen(screenState = state,
        onCreateShelfClick = {})
    }
}