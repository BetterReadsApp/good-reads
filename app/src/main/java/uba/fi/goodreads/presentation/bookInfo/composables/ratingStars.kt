package uba.fi.goodreads.presentation.bookInfo.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uba.fi.goodreads.presentation.bookInfo.BookInfoScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.bookInfo.BookInfoUIState

@Composable
fun RatingScreen(
    screenState: BookInfoUIState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "My rate", style = MaterialTheme.typography.labelMedium)
        RatingStars(
            rating = 0,
            onRatingChange = { }//newRating -> screenState.rating = newRating}
        )
    }
}

@Composable
fun RatingStars(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    var validRating = rating.coerceIn(0, 5)
    Row {
        (1..5).forEach { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star $index",
                tint = if (index <= validRating) Color.Yellow else Color.Gray, //pinta segun rating
                modifier = Modifier
                    .size(32.dp)
                    .padding(2.dp)
                    .clickable { onRatingChange(index) }
            )
        }
    }
}

@Composable
private fun OurRatingBar(rating: Float) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //esto despues tiene que ser una rating bar decente
        Row {
            RatingStars(5) { }
            Text(text = rating.toString())
        }
        Text(text = "70.677 calificaciones - 4.597 reseñas")
    }
}

@Composable
fun AvgRatingStars(
    rating: Double
) {
    val validRating = rating.coerceIn(0.0, 5.0)
    Row {
        (1..5).forEach { index ->
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .padding(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star $index (Gray)",
                    tint = Color.Gray,
                    modifier = Modifier.matchParentSize()
                )
                if (index.toDouble() <= validRating) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star $index (Yellow)",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .matchParentSize()
                            .let {
                                if (index.toFloat() - validRating < 1 && index.toFloat() > validRating) {
                                    // (validRating - (index - 1) ).coerceIn(0.0, 1.0)
                                    Modifier.clipToBounds().fillMaxWidth((validRating - (index - 1) ).coerceIn(0.0, 1.0).toFloat())
                                } else {
                                    Modifier
                                }
                            }
                    )
                }
            }
        }
        Text(text = rating.toString())
    }
    Text(text = "70.677 calificaciones - 4.597 reseñas")
}



@Preview(showBackground = true)
@Composable
fun RatingStarsPreview() {
    RatingStars(
        rating = 3,
        onRatingChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RatingScreenPreview(
    @PreviewParameter(BookInfoScreenPreviewParameterProvider::class) state: BookInfoUIState
) {
    RatingScreen(screenState = state)
}