package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.Book

object DomainBookMocks {

    fun getBooks() = listOf(
        Book(
            id = "1",
            title = "Atomic Habits",
            author = "James Clear",
            description = "No matter your goals, Atomic Habits offers a proven framework for improving - every day.",
            genres = listOf("Self help", "Non-fiction"),
            publicationDate = "21/01/2005",
            id = 1,
            pages = 420

        ),
        Book(
            id = "1",
            title = "Clean Architecture",
            author = "Rober Martin",
            description = "By applying universal rules of software architecture, you can dramatically improve developer productivity throughout the life of any software system.",
            genres = listOf("Technology", "Non-fiction"),
            publicationDate = "21/01/2005",
            id = 2,
            pages = 320
        )
    )
}