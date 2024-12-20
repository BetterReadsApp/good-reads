package uba.fi.goodreads.presentation.shelves.shelves.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import uba.fi.goodreads.presentation.shelves.shelves.ShelvesScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.shelves.shelves.ShelvesUiState
import uba.fi.goodreads.presentation.shelves.shelves.ShelvesViewModel
import uba.fi.goodreads.presentation.shelves.shelves.navigation.ShelvesDestination

@Composable
fun ShelvesRoute(
    navigate: (ShelvesDestination) -> Unit,
    viewModel: ShelvesViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect((screenState as? ShelvesUiState.Success)?.destination) {
        (screenState as? ShelvesUiState.Success)?.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    LaunchedEffect(Unit) {
        viewModel.refreshTrigger.collect {
            viewModel.loadData()
        }
    }

    ShelvesScreen(
        screenState = screenState,
        onCreateShelfClick = viewModel::onCreateShelfClick,
        onConfirmCreateShelf = viewModel::onConfirmShelfCreation,
        onShelfClick = viewModel::onShelfClick,
        onDismissDialog = viewModel::onDismissDialog,
        onShelfNameChange = viewModel::onShelfNameChange
    )
}

@Composable
fun ShelvesScreen(
    screenState: ShelvesUiState,
    onCreateShelfClick: () -> Unit,
    onShelfNameChange: (String) -> Unit,
    onShelfClick: (String) -> Unit,
    onDismissDialog: () -> Unit,
    onConfirmCreateShelf: () -> Unit,
) {
    when (screenState) {
        ShelvesUiState.Error -> FeedbackScreen(type = FeedbackType.ERROR)
        ShelvesUiState.Loading -> Loading()
        is ShelvesUiState.Success -> SuccessContent(
            screenState,
            onCreateShelfClick,
            onShelfClick = onShelfClick,
            onShelfNameChange = onShelfNameChange,
            onDismissDialog = onDismissDialog,
            onConfirmCreateShelf = onConfirmCreateShelf
        )
    }
}

@Composable
private fun SuccessContent(
    screenState: ShelvesUiState.Success,
    onCreateShelfClick: () -> Unit,
    onShelfNameChange: (String) -> Unit,
    onShelfClick: (String) -> Unit,
    onDismissDialog: () -> Unit,
    onConfirmCreateShelf: () -> Unit,
) {
    if (screenState.showCreateShelfDialog) {
        CreateShelfDialog(
            newShelfName = screenState.newShelfName,
            onShelfNameChange = onShelfNameChange,
            onDismiss = onDismissDialog,
            onConfirm = onConfirmCreateShelf
        )
    }

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
            text = stringResource(id = R.string.my_books_bottom_nav_title),
            fontSize = 32.sp
        )
        screenState.shelves.forEach { shelf ->
            ShelfPreview(shelf, onShelfClick)
            Spacer(Modifier.height(16.dp))
        }
        Spacer(Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp,
                ),
            shape = RoundedCornerShape(8.dp),
            onClick = onCreateShelfClick
        ) {
            Text(text = "Create shelf")
        }

    }
}

@Composable
private fun ShelfPreview(
    shelf: Shelf,
    onShelfClick: (String) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onShelfClick(shelf.id.toString()) }
    ) {
        Row {
            if (shelf.books.isEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://cdn-icons-png.flaticon.com/512/29/29809.png")
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .size(120.dp, 180.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Cover of one of the books present inside the shelf",
                    contentScale = ContentScale.Crop,
                )

            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(shelf.books.first().photoUrl)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .size(120.dp, 180.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Icon that indicates the shelf is empty",
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = shelf.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = shelf.numberOfBooks.toString() + " books"
                )
            }


        }
    }
}

@Composable
fun CreateShelfDialog(
    newShelfName: String,
    onShelfNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Create New Shelf") },
        text = {
            Column {
                Text("Enter the name of the new shelf:")
                androidx.compose.material3.TextField(
                    value = newShelfName,
                    onValueChange = onShelfNameChange,
                    placeholder = { Text("Shelf name") }
                )
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Create")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun ShelvesScreenPreview(
    @PreviewParameter(ShelvesScreenPreviewParameterProvider::class) state: ShelvesUiState
) {
    GoodReadsTheme {
        ShelvesScreen(screenState = state,
            onCreateShelfClick = {},
            onDismissDialog = {},
            onShelfNameChange = {},
            onConfirmCreateShelf = {},
            onShelfClick = {}
        )
    }
}