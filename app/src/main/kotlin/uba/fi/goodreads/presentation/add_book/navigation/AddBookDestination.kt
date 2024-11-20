package uba.fi.goodreads.presentation.add_book.navigation

sealed class BookDestination {
    data object Back: BookDestination()
}