package uba.fi.goodreads.presentation.profile

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainShelfMocks

class ProfileScreenPreviewParameterProvider: PreviewParameterProvider<ProfileUiState> {

    override val values: Sequence<ProfileUiState> = sequenceOf(
        getState(
            firstName = "Pedro",
            lastName = "Duzac",
            followingAmount = 10,
            followersAmount = 5,
            isOwnProfile = true
        ),
        getState(
            firstName = "Emilia",
            lastName = "Duzac",
            followingAmount = 100,
            followersAmount = 200,
            followedByMe = true
        ),
        getState(
            firstName = "Marco",
            lastName = "",
            followingAmount = 1000,
            followersAmount = 1000,
            followedByMe = false
        ),
    )

    private fun getState(
        firstName: String = "",
        lastName : String = "",
        followingAmount: Int = 0,
        followersAmount: Int = 0,
        followedByMe: Boolean = false,
        isOwnProfile: Boolean = false
    ) = ProfileUiState(
        firstName = firstName,
        lastName = lastName,
        followingAmount = followingAmount,
        followersAmount = followersAmount,
        shelves = DomainShelfMocks.getShelves(),
        followedByMe = followedByMe,
        ownProfile = isOwnProfile
    )
}