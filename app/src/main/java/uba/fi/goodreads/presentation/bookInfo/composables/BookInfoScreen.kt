package uba.fi.goodreads.presentation.bookInfo.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.ReviewScreen.composables.ReviewsScreen
import uba.fi.goodreads.presentation.bookInfo.BookInfoScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.bookInfo.BookInfoUIState
import uba.fi.goodreads.presentation.bookInfo.BookInfoViewModel


@Composable
fun BookInfoRoute(
    viewModel: BookInfoViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    BookInfoScreen(
        screenState = screenState,
        onUserRatingChange = viewModel::onUserRatingChange
    )
}

@Composable
fun BookInfoScreen(
    screenState: BookInfoUIState,
    onUserRatingChange: (Int) -> Unit,
) {
    //val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        BookCoverImage()
        TitleAndAuthor(screenState.book)
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        AvgRatingStars(screenState.book.avgRating ?: 3.5)
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        RatingBox(
           userRating = screenState.userRating,
            onUserRatingChange = onUserRatingChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        WriteReviewButton(onClick = {})
    }
}


@Composable
private fun BookCoverImage() {
    val bookCoverImage = painterResource(id = R.drawable.ficciones)
    Box(modifier = Modifier.fillMaxWidth().height(250.dp)) {
        Image(
            painter = bookCoverImage,
            contentDescription = "Book Cover",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    renderEffect = BlurEffect(
                        radiusX = 20f,
                        radiusY = 20f
                    )
                },
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = bookCoverImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .size(150.dp)
                    .background(Color.Transparent)
            )
        }

    }
}

@Composable
fun WriteReviewButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .background(Color.LightGray)
            .clickable(onClick = onClick ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Write a review",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Composable
private fun TitleAndAuthor(book: Book) {
    val title = book.title
    val author = book.author
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Autor: $author",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = book.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
@Preview(showBackground = true)
fun BookInfoScreenPreview(
    @PreviewParameter(BookInfoScreenPreviewParameterProvider::class) state: BookInfoUIState
) {
    GoodReadsTheme {
        BookInfoScreen(
            state,
            onUserRatingChange = {}
        )
    }
}