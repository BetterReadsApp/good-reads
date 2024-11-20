package uba.fi.goodreads.presentation.edit_profile.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.component.avatar.AvatarSize
import uba.fi.goodreads.core.design_system.component.avatar.BrAvatar
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.presentation.edit_profile.EditProfileScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.edit_profile.EditProfileUiState
import uba.fi.goodreads.presentation.edit_profile.EditProfileViewModel
import uba.fi.goodreads.presentation.edit_profile.navigation.EditProfileDestination
import uba.fi.goodreads.presentation.profile.ProfileViewModel

private const val Kimberly = "https://api.dicebear.com/9.x/adventurer/svg?seed=Kimberly"
private const val Liam = "https://api.dicebear.com/9.x/adventurer/svg?seed=Liam"
private const val Alexander = "https://api.dicebear.com/9.x/adventurer/svg?seed=Alexander"

@Composable
fun EditProfileRoute(
    navigate: (EditProfileDestination) -> Unit,
    viewModel: EditProfileViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    EditProfileScreen(
        screenState = screenState,
        onBack = viewModel::onBack,
        onEmailChange = viewModel::onEmailChange,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onAvatarChange = viewModel::onAvatarChange,
        onSave =  {
            viewModel.onSave()
            profileViewModel.triggerRefresh()
        },
        onAuthorCheckChange  = viewModel::onAuthorCheckChange,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    screenState: EditProfileUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAvatarChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onAuthorCheckChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                BrAvatar(
                    modifier = Modifier
                        .clickable { onAvatarChange(Kimberly) }
                        .then(
                            if (screenState.avatarUrl == Kimberly) Modifier.border(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                width = 1.dp
                            ) else Modifier
                        ),
                    size = AvatarSize.LARGE,
                    url = Kimberly
                )

                BrAvatar(
                    modifier = Modifier
                        .clickable { onAvatarChange(Liam) }
                        .then(
                            if (screenState.avatarUrl == Liam) Modifier.border(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                width = 1.dp
                            ) else Modifier
                        ),
                    size = AvatarSize.LARGE,
                    url = Liam
                )

                BrAvatar(
                    modifier = Modifier
                        .clickable { onAvatarChange(Alexander) }
                        .then(
                            if (screenState.avatarUrl == Alexander) Modifier.border(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                width = 1.dp
                            ) else Modifier
                        ),
                    size = AvatarSize.LARGE,
                    url = Alexander
                )
            }


            TextField(
                value = screenState.firstName,
                onValueChange = onFirstNameChange,
                label = { Text("First name") },
                modifier = Modifier.fillMaxWidth()
            )


            TextField(
                value = screenState.lastName,
                onValueChange = onLastNameChange,
                label = { Text("Last name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = screenState.email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            if(screenState.showAuthorCheck) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = screenState.isAuthor,
                        onCheckedChange = onAuthorCheckChange
                    )
                    Text(text = "Are you an author?")
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                onClick = {
                    onSave()
                },
                enabled = true
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun EditProfileScreenPreview(
    @PreviewParameter(EditProfileScreenPreviewParameterProvider::class) state: EditProfileUiState
) {
    GoodReadsTheme {
        EditProfileScreen(
            screenState = state,
            onFirstNameChange = {},
            onLastNameChange = {},
            onEmailChange = {},
            onAvatarChange = {},
            onSave = {},
            onAuthorCheckChange = {},
            onBack = {}
        )
    }
}