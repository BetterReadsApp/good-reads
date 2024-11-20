package uba.fi.goodreads.presentation.add_book.navigation

sealed class AddBookDestination {
    data object Back: AddBookDestination()
}