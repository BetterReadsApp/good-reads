package uba.fi.goodreads.presentation.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val buttonEnabled: Boolean = false
)