package uba.fi.goodreads.presentation.bookInfo.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uba.fi.goodreads.domain.model.UserReview


@Composable
fun WriteReviewButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .background(Color.LightGray)
            .clickable(onClick = onClick),
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
fun PreviousReview(prevReview: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick()
        }

    ) {
        Text(
            text = "Your review:",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Gray)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = prevReview,
                color = Color.White
            )
        }
    }
}

@Composable
fun UsersReviewList(comments: List<UserReview>) {
    Text(
        text = "Readers' Reviews:",
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black
    )
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(comments) { comment ->
                Text(
                    text = comment.text,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}