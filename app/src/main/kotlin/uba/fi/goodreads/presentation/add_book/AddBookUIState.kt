package uba.fi.goodreads.presentation.add_book

import uba.fi.goodreads.domain.model.Book

data class AddBookUIState(
    //val destination: AddBookDestination? = null,
    val book: Book = Book(
        author = "",
        description = "",
        genres = emptyList(),
        publicationDate = "",
        title = "",
        avgRating = null,
        id = "1",
        pages = 1,
        yourRating = null,
        yourReview = null,
        hasQuizzes = false,
        photoUrl = ""
    ),
    val reviews: List<String> = emptyList()
)
