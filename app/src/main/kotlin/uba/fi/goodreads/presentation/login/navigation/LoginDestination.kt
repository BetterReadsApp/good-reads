package uba.fi.goodreads.presentation.login.navigation

sealed class LoginDestination {
    data object Register: LoginDestination()
}