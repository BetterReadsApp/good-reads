package uba.fi.goodreads.presentation.edit_book.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.presentation.edit_book.EditBookUIState
import uba.fi.goodreads.presentation.edit_book.EditBookViewModel
import uba.fi.goodreads.presentation.edit_book.navigation.EditBookDestination
import uba.fi.goodreads.presentation.book_info.composables.BookCoverImage


@Composable
fun EditBookRoute(
    navigate: (EditBookDestination) -> Unit,
    viewModel: EditBookViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }
    EditBookScreen(
        screenState = screenState,
        onCoverUrlChange = viewModel::onCoverUrlChange,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSaveBookClick = viewModel::onSaveChangesClick,
        onBack = viewModel::onBack,
        onPagesChange = viewModel::onPagesChange,
        onDeleteBookClick = viewModel::onDeleteBookClick
    )
}

@Composable
fun EditBookScreen(
    screenState: EditBookUIState,
    onCoverUrlChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPagesChange: (String) -> Unit,
    onBack: () -> Unit,
    onSaveBookClick: () -> Unit,
    onDeleteBookClick: () -> Unit,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        EditBookTopBar(
            onBack = onBack,
        )


        BookCoverImage(if (screenState.coverUrl.isNotBlank()) screenState.coverUrl else "https://via.placeholder.com/200x200.png?text=Sin+portada")

        Spacer(modifier = Modifier.height(16.dp))
        InputBox(screenState.coverUrl, "cover URL", onCoverUrlChange)

        Spacer(modifier = Modifier.height(8.dp))
        InputBox(screenState.title, "Title", onTitleChange)

        Spacer(modifier = Modifier.height(8.dp))
        InputBox(screenState.description, "Summary", onDescriptionChange)

        Spacer(modifier = Modifier.height(8.dp))
        NumericInput(screenState.pages.toString(), "Pages", onPagesChange)

        Spacer(modifier = Modifier.height(16.dp))

        EditScreenButton(onSaveBookClick, Color.Unspecified, "Save changes")
        EditScreenButton(
            onClick = {showDeleteDialog = true},
            Color.Red,
            "Delete book")
    }
    if (showDeleteDialog) {
        DeleteDialog(
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                showDeleteDialog = false
                onDeleteBookClick()
            }
        )
    }

}

@Composable
private fun DeleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Book") },
        text = { Text("Are you sure you want to delete this book? This is irreversible.") },
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
private fun EditScreenButton(
    onClick: () -> Unit,
    color: Color,
    text: String,
    ) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text(text)
    }
}

@Composable
private fun InputBox(
    value: String,
    label: String,
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

@Composable
private fun NumericInput(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value.ifBlank { "" },
        onValueChange = { input ->
            if (input.isBlank()) {
                onValueChange("0")
            } else if (input.all { it.isDigit() } && input.isNotBlank()) {
                onValueChange(input)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        label = { Text(label) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookTopBar(
    onBack: () -> Unit,
) {
    TopAppBar(
        title = { Text("Edit book") },
        navigationIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Back"
                )
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun EditBookScreenPreview() {
    val previousState = EditBookUIState()

    EditBookScreen(
        screenState = previousState,
        onBack = {},
        onTitleChange = {},
        onCoverUrlChange = {},
        onDescriptionChange = {},
        onSaveBookClick = {},
        onPagesChange = {},
        onDeleteBookClick = {}
    )
}