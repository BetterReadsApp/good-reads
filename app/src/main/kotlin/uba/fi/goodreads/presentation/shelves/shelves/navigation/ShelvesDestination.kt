package uba.fi.goodreads.presentation.shelves.shelves.navigation

sealed class ShelvesDestination {
    data class ShelfBooks(val shelfId: String) : ShelvesDestination()
}