package uba.fi.goodreads.presentation.shelves.shelvesScreen.navigation

sealed class ShelvesDestination {
    data class ShelfBooks(val id: Int) : ShelvesDestination()
}