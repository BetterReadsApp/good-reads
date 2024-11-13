package uba.fi.goodreads.presentation.search.search.navigation

sealed class SearchDestination {
    data class BookInfo(val id: String) : SearchDestination()
    data class Profile(val id: Int) : SearchDestination()
    data class Genre(val genre: String): SearchDestination()
}