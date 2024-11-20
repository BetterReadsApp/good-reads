package uba.fi.goodreads.presentation.add_book.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import uba.fi.goodreads.presentation.add_book.AddBookUIState
import uba.fi.goodreads.presentation.add_book.AddBookViewModel
import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination


@Composable
fun AddBookRoute(
    navigate: (AddBookDestination) -> Unit,
    viewModel: AddBookViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    AddBookScreen(
        screenState = screenState,
        onCoverUrlChange = viewModel::onCoverUrlChange,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSaveBookClick = viewModel::onSaveBookClick,
        onBack = viewModel::onBack
    )
}

@Composable
fun AddBookScreen(
    screenState: AddBookUIState,
    onCoverUrlChange: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onBack: () -> Unit,
    onSaveBookClick: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            onBack = onBack,
        )
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {

            val painter = rememberAsyncImagePainter(
                model = if (screenState.coverUrl.isNotBlank()) screenState.coverUrl else "https://via.placeholder.com/200x200.png?text=Sin+portada"
            )
            Image(
                painter = painter,
                contentDescription = "Imagen de portada",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = screenState.coverUrl,
            onValueChange = onCoverUrlChange,
            label = { Text("URL de portada") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Input para el título
        OutlinedTextField(
            value = screenState.title,
            onValueChange = onTitleChange,
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Input para la descripción
        OutlinedTextField(
            value = screenState.description,
            onValueChange = onDescriptionChange,
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSaveBookClick,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text("Guardar libro")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onBack: () -> Unit,
) {
    TopAppBar(
        title = { Text("Agregar Libro") },
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
fun ReviewsScreenPreview() {
    AddBookScreen(
        screenState = AddBookUIState(),
        onBack = {},
        onTitleChange = {},
        onCoverUrlChange = {},
        onDescriptionChange = {},
        onSaveBookClick = {},
    )
}