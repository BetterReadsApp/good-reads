package uba.fi.goodreads.presentation.login

import uba.fi.goodreads.presentation.login.navigation.LoginDestination

data class LoginUiState(
    val destination: LoginDestination? = null,
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val buttonEnabled: Boolean = false
)