package uba.fi.goodreads.presentation.shelves.add_book.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
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
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.shelves.add_book.AddBookToShelvesParameterProvider
import uba.fi.goodreads.presentation.shelves.add_book.AddBookToShelvesUiState
import uba.fi.goodreads.presentation.shelves.add_book.AddBookToShelvesViewModel
import uba.fi.goodreads.presentation.shelves.add_book.navigation.AddBookToShelfDestination
import uba.fi.goodreads.presentation.shelves.shelf_books.composables.BookSummary
import uba.fi.goodreads.presentation.shelves.shelves.ShelvesViewModel

@Composable
fun AddToShelvesRoute(
    navigate: (AddBookToShelfDestination) -> Unit,
    viewModel: AddBookToShelvesViewModel = hiltViewModel(),
    shelvesViewModel: ShelvesViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    AddToShelvesScreen(
        screenState = screenState,
        selectedShelves = screenState.selectedShelves,
        onConfirm =  {
            viewModel.onConfirm()
            shelvesViewModel.triggerRefresh()
        },
        onShelfClick = viewModel::onShelfClick,
    )

}


@Composable
private fun AddToShelvesScreen(
    screenState: AddBookToShelvesUiState,
    onConfirm: () -> Unit,
    onShelfClick: (String, Boolean) -> Unit,
    selectedShelves: Set<String>,
) {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(16.dp))
        BookSummary(screenState.book, {})
        Spacer(Modifier.padding(16.dp))

        Text(
            text = "SELECT A SHELF",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        screenState.shelves.forEach { shelf ->
            ShelfButton(
                shelf,
                isSelected = selectedShelves.contains(shelf.id.toString())
            )
            { isSelected ->
                onShelfClick(shelf.id.toString(), isSelected)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        ConfirmButton(onClick = onConfirm)
    }


}


@Composable
fun ShelfButton(
    shelf: Shelf,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {

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
            checked = isSelected,
            onCheckedChange = {
                onSelectionChange(it)
            }
        )
    }

}


@Composable
fun ConfirmButton(onClick: () -> Unit) {
    Box(
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
            onConfirm = {},
            onShelfClick = { _, _ -> }
        )
    }
}