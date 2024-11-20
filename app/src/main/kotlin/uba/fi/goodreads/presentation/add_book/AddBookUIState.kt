package uba.fi.goodreads.presentation.add_book

import uba.fi.goodreads.domain.model.BookGenre
import uba.fi.goodreads.presentation.add_book.navigation.AddBookDestination

data class AddBookUIState(
    val destination: AddBookDestination? = null,
    val coverUrl: String = "",
    val title: String = "",
    val description: String = "",
    val genre: BookGenre = BookGenre.Thriller,
    val pages: Int = 0,
    val publicationDate: String = "2010-10-10",

    )
