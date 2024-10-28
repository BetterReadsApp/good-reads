package uba.fi.goodreads.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.rememberAsyncImagePainter
import uba.fi.goodreads.R

@Composable
fun AsyncImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: Painter = painterResource(R.drawable.ic_avatar_fill), // TODO Change
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            isLoading = state is Loading
            isError = state is Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && !isLocalInspection) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = placeholder,
                contentDescription = contentDescription,
            )
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
            contentDescription = contentDescription,
        )
    }
}