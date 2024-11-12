package uba.fi.goodreads.presentation.register

import uba.fi.goodreads.presentation.register.navigation.RegisterDestination

data class RegisterUiState(
    val destination: RegisterDestination? = null,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val isError: Boolean = false,
    val isAuthor: Boolean = false,
    val buttonEnabled: Boolean = false,
)