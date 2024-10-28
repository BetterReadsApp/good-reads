package uba.fi.goodreads.presentation.profile

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ProfileScreenPreviewParameterProvider: PreviewParameterProvider<ProfileUiState> {

    override val values: Sequence<ProfileUiState> = sequenceOf(
        getState(
            email = "",
            password = ""
        ),
        getState(
            email = "email@email.com",
            password = ""
        ),
        getState(
            email = "email@email.com",
            password = "Password"
        ),
    )

    private fun getState(
        email: String,
        password: String
    ) = ProfileUiState(
        email = email,
        password = password,
    )
}