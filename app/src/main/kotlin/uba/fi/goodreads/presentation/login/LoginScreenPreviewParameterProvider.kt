package uba.fi.goodreads.presentation.login

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.mocks.DomainPostMocks

class LoginScreenPreviewParameterProvider: PreviewParameterProvider<LoginUiState> {

    override val values: Sequence<LoginUiState> = sequenceOf(
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
    ) = LoginUiState(
        email = email,
        password = password,
    )
}