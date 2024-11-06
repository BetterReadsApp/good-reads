package uba.fi.goodreads.presentation.review.navigation

sealed class ReviewDestination {
    data object Back: ReviewDestination()
}