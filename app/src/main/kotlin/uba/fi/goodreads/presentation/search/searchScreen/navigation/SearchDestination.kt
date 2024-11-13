package uba.fi.goodreads.presentation.search.searchScreen.navigation

sealed class SearchDestination {
    data class BookInfo(val id: String) : SearchDestination()
    data class Profile(val id: Int) : SearchDestination()
    data class Genre(val id: String): SearchDestination()
}