package uba.fi.goodreads.presentation.shelves.addBookScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.shelves.addBookScreen.AddBookToShelvesParameterProvider
import uba.fi.goodreads.presentation.shelves.addBookScreen.AddBookToShelvesUiState


@Composable
private fun AddToShelvesScreen(
    screenState: AddBookToShelvesUiState,
    onConfirm: () -> Unit,
    selectedShelves: MutableSet<String>,
) {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookSummary(screenState.book)
        Text(
            text = "SELECT A SHELF",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        screenState.shelves.forEach{ shelf ->
            ShelfButton(shelf, isSelected = shelf.name in selectedShelves)
            { isSelected ->
                if (isSelected) {
                    selectedShelves.add(shelf.name)
                } else {
                    selectedShelves.remove(shelf.name)
                }
            }
            }
        Spacer(modifier = Modifier.height(16.dp))

        ConfirmButton(onClick = onConfirm)
        }


}



@Composable
fun ShelfButton(shelf: Shelf,
                isSelected: Boolean,
                onSelectionChange: (Boolean) -> Unit){

    var selected: Boolean by remember { mutableStateOf(isSelected) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = shelf.name,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = shelf.numberOfBooks.toString() + "libros",
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 16.dp)
        )

        Checkbox(
            checked = selected,
            onCheckedChange = {
                selected = it
                onSelectionChange(it)
            }
        )
    }

}

@Composable
fun BookSummary(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
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
fun ConfirmButton(onClick: () -> Unit) {
    Box (
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .background(Color.LightGray)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Confirm Selection",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}



@Composable
@Preview(showBackground = true)
fun AddToShelvesScreenPreview(
    @PreviewParameter(AddBookToShelvesParameterProvider::class) state: AddBookToShelvesUiState
) {
    GoodReadsTheme {
        AddToShelvesScreen(
            screenState = state,
            selectedShelves = mutableSetOf(),
            onConfirm = {}
        )
    }
}