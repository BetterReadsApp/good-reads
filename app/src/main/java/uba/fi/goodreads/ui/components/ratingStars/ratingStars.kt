package uba.fi.goodreads.ui.components.ratingStars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RatingScreen(
    ratingVewModel: RatingViewModel = viewModel()
) {
    val rating by ratingVewModel.rating.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "My rate", style = MaterialTheme.typography.labelMedium)

        RatingStars(
            rating = rating,
            onRatingChange = { newRating -> ratingVewModel.updateRating(newRating) }
        )
    }
}

@Composable
fun RatingStars(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    val validRating = rating.coerceIn(0, 5)
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

class RatingStarsPreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int> = sequenceOf(0, 1, 2, 3, 4, 5)
}

@Preview(showBackground = true)
@Composable
fun RatingStarsPreview(
    @PreviewParameter(RatingStarsPreviewParameterProvider::class) rating: Int
) {
    RatingStars(
        rating = rating,
        onRatingChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RatingScreenPreview(
    @PreviewParameter(RatingStarsPreviewParameterProvider::class) rating: Int
) {
    val previewViewModel = RatingViewModel().apply { updateRating(rating) }
    RatingScreen(previewViewModel)
}