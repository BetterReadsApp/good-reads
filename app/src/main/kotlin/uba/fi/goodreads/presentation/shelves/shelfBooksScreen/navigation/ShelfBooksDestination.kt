package uba.fi.goodreads.presentation.shelves.shelfBooksScreen.navigation

sealed class ShelfBooksDestination {
    data class BookInfo(val id: Int) : ShelfBooksDestination()
    data object Back: ShelfBooksDestination()
}