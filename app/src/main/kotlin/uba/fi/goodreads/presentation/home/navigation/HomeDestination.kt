package uba.fi.goodreads.presentation.home.navigation

sealed class HomeDestination {
    data class BookInfo(val id: Int) : HomeDestination()
}