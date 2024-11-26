package uba.fi.goodreads.presentation.shelves.shelf_books.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.shelves.shelf_books.ShelfBooksUiState
import uba.fi.goodreads.presentation.shelves.shelf_books.ShelfBooksViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
        onBookClick = viewModel::onBookClick,
        onRenameShelf = viewModel::onRenameShelf,
        onDeleteShelf = viewModel::onDeleteShelf
    )
}

@Composable
private fun ShelfBooksScreen(
    screenState: ShelfBooksUiState,
    onBookClick: (String) -> Unit,
    onRenameShelf: (String) -> Unit,
    onDeleteShelf: () -> Unit
) {
    val scrollState = rememberScrollState()
    var showMenu by remember { mutableStateOf(false) }
    var showRenameDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = screenState.name,
                fontSize = 32.sp,
                modifier = Modifier.weight(1f)
            )
            Box{
                IconButton(onClick = { showMenu = true }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)

                ){
                    DropdownMenuItem(
                        onClick = {
                            showMenu = false
                            showRenameDialog = true
                        },
                        text = { Text("Rename") }
                    )
                    DropdownMenuItem(onClick = {
                        showMenu = false
                        showDeleteDialog = true
                    },
                        text = { Text("Delete") }
                    )
                }
            }

        }
        screenState.books.forEach { book ->
            BookSummary(book, onBookClick = onBookClick)
            Spacer(Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.fillMaxHeight().weight(1f))
    }

    if (showRenameDialog) {
        RenameDialog(
            currentName = screenState.name,
            onDismiss = { showRenameDialog = false },
            onConfirm = { newName ->
                showRenameDialog = false
                onRenameShelf(newName)
            }
        )
    }

    if (showDeleteDialog) {
        DeleteDialog(
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                showDeleteDialog = false
                onDeleteShelf()
            }
        )
    }
}


@Composable
private fun RenameDialog(
    currentName: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var newName by remember { mutableStateOf(currentName) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Rename Shelf") },
        text = {
            Column {
                Text("Enter a new name for the shelf:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = newName,
                    onValueChange = { newName = it },
                    placeholder = { Text("New shelf name") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(newName) }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun DeleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Shelf") },
        text = { Text("Are you sure you want to delete this shelf?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
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
                    .data(book.photoUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(100.dp, 160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = "Cover of one of the books present inside the shelf",
                contentScale = ContentScale.Crop,
            )

            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = book.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "from " + book.author
                )
                Text(
                    text = "you rated it " + book.yourRating + "/5"
                )

                Text(
                    text = "average rating: " + book.avgRating
                )
                Text(
                    text = "original publication date " + book.publicationDate
                )
                Text(
                    text = "" + book.pages + "pages"
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
            onBookClick = { },
            onRenameShelf = {},
            onDeleteShelf = {}
        )
    }
}