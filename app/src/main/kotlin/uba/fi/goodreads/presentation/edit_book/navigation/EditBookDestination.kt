package uba.fi.goodreads.presentation.add_book.navigation

import uba.fi.goodreads.presentation.add_book.EditBookViewModel

sealed class EditBookDestination {
    data object Back: EditBookDestination()
}