package uba.fi.goodreads.presentation.profile.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.component.avatar.AvatarSize
import uba.fi.goodreads.core.design_system.component.avatar.BrAvatar
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.profile.ProfileScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.profile.ProfileUiState
import uba.fi.goodreads.presentation.profile.ProfileViewModel
import uba.fi.goodreads.presentation.profile.navigation.ProfileDestination

@Composable
fun ProfileRoute(
    navigate: (ProfileDestination) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    ProfileScreen(
        screenState = screenState,
        onBack = viewModel::onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    screenState: ProfileUiState,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        painterResource(
                            id = R.drawable.ic_back
                        ),
                        contentDescription = null
                    )
                }
            },
            title = {}
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Header(
                ownProfile = true,
                following = false,
                pictureUrl = null,
                name = screenState.firstName + " " + screenState.lastName,
                followersAmount = screenState.followersAmount.toString(),
                followingAmount = screenState.followingAmount.toString(),
            )

            Shelves(screenState.shelves)

            RatedBooks(screenState.ratedBooks)
        }

    }
}


@Composable
private fun ColumnScope.Header(
    ownProfile: Boolean,
    following: Boolean,
    pictureUrl: String?,
    name: String,
    followersAmount: String,
    followingAmount: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        BrAvatar(
            size = AvatarSize.LARGE,
            url = pictureUrl
        )
    }

    Spacer(Modifier.height(4.dp))

    Text(
        text = name
    )

    Spacer(Modifier.height(4.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
            FollowersTextColumn("Followers", followersAmount)
            FollowersTextColumn("Following", followingAmount)
    }

    Spacer(Modifier.height(8.dp))

    HorizontalDivider(
        thickness = 1.dp,
        color = Color.LightGray
    )
}


@Composable
private fun FollowersTextColumn(
    title: String,
    amount: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = title
        )
        Text(
            text = amount
        )
    }
}

@Composable
private fun ColumnScope.Shelves(
    shelves: List<Shelf>
) {
    Text(
        text = "Shelves"
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(shelves) { shelf ->
            Shelf(shelf)
        }
    }
}

@Composable
private fun Shelf(shelf: Shelf) {
    Column {
        Text(
            text = shelf.name
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://f.media-amazon.com/images/I/41tjPqycZ1L._SY445_SX342_.jpg") // TODO AL MODEL
                .crossfade(true)
                .build(),
            // placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "Cover of one of the books present inside the shelf",
            contentScale = ContentScale.Crop,
        )

        Text(
            text = shelf.numberOfBooks.toString()
        )
    }
}

@Composable
private fun ColumnScope.RatedBooks(
    ratedBooks: List<Book>
) {
    Text(
        text = "Rated books"
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(ratedBooks) { book ->
            Book(book)
        }
    }
}

@Composable
private fun Book(book: Book) {
    Column {
        Text(
            text = book.title
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://f.media-amazon.com/images/I/41tjPqycZ1L._SY445_SX342_.jpg") // TODO AL MODEL
                .crossfade(true)
                .build(),
            // placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "Cover of one of the books present inside the shelf",
            contentScale = ContentScale.Crop,
        )

        Text(
            text = book.author
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview(
    @PreviewParameter(ProfileScreenPreviewParameterProvider::class) state: ProfileUiState
) {
    GoodReadsTheme {
        ProfileScreen(
            screenState = state,
            onBack = {}
        )
    }
}