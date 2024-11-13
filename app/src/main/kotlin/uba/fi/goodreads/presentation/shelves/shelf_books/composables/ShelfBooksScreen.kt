package uba.fi.goodreads.presentation.shelves.shelf_books.composables

import androidx.compose.foundation.clickable
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.shelves.shelf_books.ShelfBooksUiState
import uba.fi.goodreads.presentation.shelves.shelf_books.ShelfBooksViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.presentation.shelves.shelf_books.ShelfBooksPreviewParameterProvider
import uba.fi.goodreads.presentation.shelves.shelf_books.navigation.ShelfBooksDestination

@Composable
fun ShelfBooksRoute(
    navigate: (ShelfBooksDestination) -> Unit,
    viewModel: ShelfBooksViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    ShelfBooksScreen(
        screenState = screenState,
        onBookClick = viewModel::onBookClick
    )
}

@Composable
private fun ShelfBooksScreen(
    screenState: ShelfBooksUiState,
    onBookClick: (String) -> Unit
) {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 32.dp),
            text = screenState.name,
            fontSize = 32.sp
        )
        screenState.books.forEach { book ->
            BookSummary(book, onBookClick = onBookClick)
            Spacer(Modifier.height(16.dp))
        }
        Spacer(Modifier.weight(1f))

    }
}

@Composable
fun BookSummary(book: Book, onBookClick: (String) -> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onBookClick(book.id) }
    ) {
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
            Column {
                Text(
                    text = book.title,
                )
                Text(
                    text = "from " + book.author
                )
                Text(
                    text = "average rating: " + book.avgRating
                )
                Text(
                    text = "original publication date " + book.publicationDate
                )
            }


        }
    }
}


@Composable
@Preview(showBackground = true)
fun ShelvesScreenPreview(
    @PreviewParameter(ShelfBooksPreviewParameterProvider::class) state: ShelfBooksUiState
) {
    GoodReadsTheme {
        ShelfBooksScreen(
            screenState = state,
            onBookClick = { }
        )
    }
}