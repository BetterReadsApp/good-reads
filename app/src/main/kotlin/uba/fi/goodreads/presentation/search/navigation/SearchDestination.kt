package uba.fi.goodreads.presentation.search.navigation

sealed class SearchDestination {
    data class BookInfo(val id: String) : SearchDestination()
    data class Profile(val id: Int) : SearchDestination()
}