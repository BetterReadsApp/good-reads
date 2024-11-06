package uba.fi.goodreads.presentation.bookInfo.navigation

sealed class BookInfoDestination {
    data class Review(val bookId: String) : BookInfoDestination()
}