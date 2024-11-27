package uba.fi.goodreads.presentation.add_book.composables

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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.presentation.add_book.AddBookUIState
import uba.fi.goodreads.presentation.add_book.AddBookViewModel
import uba.fi.goodreads.presentation.add_book.EditBookUIState
import uba.fi.goodreads.presentation.add_book.EditBookViewModel
import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination
import uba.fi.goodreads.presentation.add_book.navigation.EditBookDestination
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
        onPagesChange = viewModel::onPagesChange
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
) {

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
        InputBox(screenState.coverUrl, "URL de la portada", onCoverUrlChange)

        Spacer(modifier = Modifier.height(8.dp))
        InputBox(screenState.title, "Título", onTitleChange)

        Spacer(modifier = Modifier.height(8.dp))
        InputBox(screenState.description, "Descripción", onDescriptionChange)

        Spacer(modifier = Modifier.height(8.dp))
        NumericInput(screenState.pages.toString(), "Páginas", onPagesChange)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSaveBookClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text("Guardar cambios")
        }
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
        value = if (value.isBlank()) "" else value,
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
        title = { Text("Editar Libro") },
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
        onPagesChange = {}
    )
}