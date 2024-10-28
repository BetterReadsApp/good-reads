package uba.fi.goodreads.presentation.profile.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.component.avatar.AvatarSize
import uba.fi.goodreads.core.design_system.component.avatar.BrAvatar
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
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
    }

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

        /*Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                )
        ) {
            profile.ownProfile?.let {
                AvatarAndButtonRow(
                    ownProfile = it,
                    following = profile.follows,
                    pictureUrl = profile.user.pictureUrl,
                    onProfileButtonClick = onFollowButtonClick,
                    onSettingsClick = onSettingsClick
                )
            

            NameInfo(
                name = "${profile.user.firstName} ${profile.user.lastName}",
                userName = "@${profile.user.userName}",
            )

            FollowersRow(
                followers = profile.user.followers?.toString() ?: "0",
                following = profile.user.following?.toString() ?: "0",
                onFollowersClick = onFollowersClick,
                onFollowingClick = onFollowingClick
            )
        }

        HorizontalDivider(
            thickness = TwTheme.borders.border1,
            color = TwTheme.colors.borderMuted
        )
    }


    profileFeed(
        myFeed = profileFeed,
        onRtClick = onRtClick,
        onLikeClick = onLikeClick,
        onBookmarkClick = onBookmarkClick
    )*/

}

@Composable
private fun AvatarAndButtonRow(
    ownProfile: Boolean,
    following: Boolean,
    pictureUrl: String?,
    onProfileButtonClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrAvatar(
            size = AvatarSize.LARGE,
            url = pictureUrl
        )

        Spacer(Modifier.weight(1f))
/*
        if (ownProfile)
            SettingsButton(onSettingsClick)
        else
            TwFollowButton(
                follows = following,
                onClick = onProfileButtonClick
            )*/
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