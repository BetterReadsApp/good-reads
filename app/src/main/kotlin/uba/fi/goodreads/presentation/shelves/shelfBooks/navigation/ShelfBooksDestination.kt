package uba.fi.goodreads.presentation.shelves.shelfBooks.navigation

sealed class ShelfBooksDestination {
    data class BookInfo(val id: Int) : ShelfBooksDestination()
}