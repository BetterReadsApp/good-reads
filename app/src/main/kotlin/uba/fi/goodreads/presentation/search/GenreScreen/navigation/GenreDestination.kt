package uba.fi.goodreads.presentation.search.searchScreen.navigation

sealed class GenreDestination {
    data class BookInfo(val id: String) : GenreDestination()
}