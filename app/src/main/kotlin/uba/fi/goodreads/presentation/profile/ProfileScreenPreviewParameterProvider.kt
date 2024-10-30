package uba.fi.goodreads.presentation.profile

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainShelfMocks

class ProfileScreenPreviewParameterProvider: PreviewParameterProvider<ProfileUiState> {

    override val values: Sequence<ProfileUiState> = sequenceOf(
        getState(
            firstName = "Juan",
            lastName = "Duzac",
            followingAmount = 10,
            followersAmount = 5,
        ),
        getState(
            firstName = "Emilia",
            lastName = "Duzac",
            followingAmount = 100,
            followersAmount = 200,
        ),
    )

    private fun getState(
        firstName: String = "",
        lastName : String = "",
        followingAmount: Int = 0,
        followersAmount: Int = 0,
    ) = ProfileUiState(
        firstName = firstName,
        lastName = lastName,
        followingAmount = followingAmount,
        followersAmount = followersAmount,
        shelves = DomainShelfMocks.getShelves(),
    )
}