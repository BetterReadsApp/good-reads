package uba.fi.goodreads.core.design_system.component.book_row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.model.Book

@Composable
fun BookRow(
    book: Book,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(80.dp)
                .height(120.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.photoUrl) // TODO AL MODEL
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ficciones),
            contentDescription = book.description,
            contentScale = ContentScale.Crop,
        )

        Spacer(Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "by ${book.author}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
@Preview
private fun BookRowPreview() {
    GoodReadsTheme {
        BookRow(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            book = DomainBookMocks.getBooks().first()
        )
    }
}