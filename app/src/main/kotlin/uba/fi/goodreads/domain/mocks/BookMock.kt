package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.BookGenre

object BookMock {
    fun getBook() = Book(
        id = "1",
        title = "Atomic Habits",
        author = "James Clear",
        description = "No matter your goals, Atomic Habits offers a proven framework for improving - every day.",
        publicationDate = "21/10/2003",
        pages = 420,
        genres = listOf(BookGenre.SciFi, BookGenre.Biography),
        hasQuizzes = false,
        photoUrl = ""
    )

}