package uba.fi.goodreads.presentation.shelves.add_book.navigation

sealed class AddBookToShelfDestination {
    data object Back: AddBookToShelfDestination()
}