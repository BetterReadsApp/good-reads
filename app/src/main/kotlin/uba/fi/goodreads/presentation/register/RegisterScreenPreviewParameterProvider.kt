package uba.fi.goodreads.presentation.register

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainPostMocks
import uba.fi.goodreads.presentation.login.LoginUiState

class RegisterScreenPreviewParameterProvider: PreviewParameterProvider<RegisterUiState> {

    override val values: Sequence<RegisterUiState> = sequenceOf(
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
    ) = RegisterUiState(
        email = email,
        password = password,
    )
}