package uba.fi.goodreads.presentation.add_book

import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination

data class AddBookUIState(
    val destination: AddBookDestination? = null,
    val coverUrl: String = "",
    val title: String = "",
    val description: String = "",
)
