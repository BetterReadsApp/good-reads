package uba.fi.goodreads.presentation.shelves.shelf_books.navigation

sealed class ShelfBooksDestination {
    data class BookInfo(val id: String) : ShelfBooksDestination()
    data object Back: ShelfBooksDestination()
}