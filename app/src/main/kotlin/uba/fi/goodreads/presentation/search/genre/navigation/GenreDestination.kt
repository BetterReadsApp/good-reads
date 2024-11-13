package uba.fi.goodreads.presentation.search.search.navigation

sealed class GenreDestination {
    data class BookInfo(val id: String) : GenreDestination()
}