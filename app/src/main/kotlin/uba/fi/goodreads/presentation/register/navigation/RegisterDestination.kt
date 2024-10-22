package uba.fi.goodreads.presentation.register.navigation

sealed class RegisterDestination {
    data object Login: RegisterDestination()
}