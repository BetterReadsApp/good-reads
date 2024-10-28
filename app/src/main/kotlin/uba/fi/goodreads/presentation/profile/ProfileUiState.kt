package uba.fi.goodreads.presentation.profile

import uba.fi.goodreads.presentation.profile.navigation.ProfileDestination

data class ProfileUiState(
    val destination: ProfileDestination? = null,
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val buttonEnabled: Boolean = false
)