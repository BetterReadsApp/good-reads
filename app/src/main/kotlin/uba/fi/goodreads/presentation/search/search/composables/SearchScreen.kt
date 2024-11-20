package uba.fi.goodreads.presentation.search.search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.core.design_system.component.book_row.BookRow
import uba.fi.goodreads.core.design_system.component.user_card.UserCard
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.BookGenre
import uba.fi.goodreads.presentation.search.search.SearchScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.search.search.SearchUiState
import uba.fi.goodreads.presentation.search.search.SearchViewModel
import uba.fi.goodreads.presentation.search.search.navigation.SearchDestination

@Composable
fun SearchRoute(
    navigate: (SearchDestination) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    SearchScreen(
        screenState = screenState,
        onSearchChange = viewModel::onSearchChange,
        onBookClick = viewModel::onBookClick,
        onUserClick = viewModel::onUserClick,
        onGenreClick = viewModel::onGenreClick
    )
}

@Composable
fun SearchScreen(
    screenState: SearchUiState,
    onSearchChange: (String) -> Unit,
    onBookClick: (String) -> Unit,
    onUserClick: (Int) -> Unit,
    onGenreClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(32.dp))

        TextField(
            value = screenState.search,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search") }
        )

        if (screenState.loading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {


            if (screenState.search.isNotBlank()) {
                if (screenState.users.isNotEmpty()) {
                    Text(
                        text = "Users",
                        modifier = Modifier.padding(
                            start = 16.dp,
                            bottom = 16.dp,
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        items(screenState.users) { user ->
                            UserCard(
                                pictureUrl = user.avatarUrl,
                                firstName = user.name,
                                lastName = user.lastName,
                                onCardClick = { onUserClick(user.id) }
                            )
                            Spacer(Modifier.width(16.dp))
                        }
                    }
                }

                if (screenState.books.isNotEmpty()) {
                    Text(
                        text = "Books",
                        modifier = Modifier.padding(
                            top = 16.dp,
                            start = 16.dp,
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(screenState.books) { book ->
                            BookRow(
                                book = book,
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onBookClick(book.id) }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            } else {
                Text(
                    text = "Explore by Genre",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(BookGenre.entries) { genre ->
                        GenreItem(genre.toString(), onClick = { onGenreClick(genre.toString()) })
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun GenreItem(genre: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = genre, modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "search books of the $genre genre"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewParameterProvider::class) state: SearchUiState
) {
    GoodReadsTheme {
        SearchScreen(
            state,
            onBookClick = {},
            onSearchChange = {},
            onUserClick = {},
            onGenreClick = {}
        )
    }
}