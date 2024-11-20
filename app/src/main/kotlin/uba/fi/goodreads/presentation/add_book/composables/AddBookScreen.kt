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
import coil.compose.rememberAsyncImagePainter
import uba.fi.goodreads.presentation.add_book.AddBookViewModel
import uba.fi.goodreads.presentation.review.ReviewViewModel
import uba.fi.goodreads.presentation.review.navigation.ReviewDestination

@Composable
fun ReviewRoute(
    navigate: (ReviewDestination) -> Unit,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    //val screenState by viewModel.screenState.collectAsState()

    //LaunchedEffect(screenState.destination) {
    //    screenState.destination?.let { destination ->
    //        navigate(destination)
    //        viewModel.onClearDestination()
    //    }
    //}

//    ReviewScreen(
//        previousReview = screenState.book.yourReview ?: "",
//        onBack = viewModel::onBack,
//        onReviewChange = viewModel::onReviewChange
//    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(
//    onBack: () -> Unit,
//    onReviewChange: (String) -> Unit,
    //viewModel: AddBookViewModel = hiltViewModel(),
) {
    var url by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
//        TopBar(
//            onBack = onBack,
//            onSave = {
//                onReviewChange(text)
//                onBack()
//            }
//        )
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            // Mostrar la imagen de portada o la imagen por defecto
            val painter = rememberAsyncImagePainter(
                //model = if (viewModel.coverUrl.value.isNotBlank()) viewModel.coverUrl.value else "https://via.placeholder.com/200x200.png?text=Sin+portada"
                model = "https://via.placeholder.com/200x200.png?text=Sin+portada"
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
            value = url, //viewModel.coverUrl.value,
            onValueChange = { url = it}, //viewModel.coverUrl.value = it },
            label = { Text("URL de portada") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Input para el título
        OutlinedTextField(
            value = title,//viewModel.title.value,
            onValueChange = { title = it},//viewModel.title.value = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Input para la descripción
        OutlinedTextField(
            value = description,//viewModel.description.value,
            onValueChange = { description = it},//viewModel.description.value = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
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
    onSave: () -> Unit
) {
    TopAppBar(
        title = { Text("Write a review") },
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
        actions = {
            IconButton(
                onClick = {onSave()}
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Review"
                )
            }
        }
    )
}

@Composable
fun ReviewTextField(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        label = { Text("Write a review") },
        modifier = Modifier.fillMaxSize()
    )
}







@Composable
@Preview(showBackground = true)
fun ReviewsScreenPreview() {
    AddBookScreen()
}