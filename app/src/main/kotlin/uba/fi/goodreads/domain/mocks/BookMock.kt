package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.Book

object BookMock {
    fun getbook() = Book(
        title = "Atomic Habits",
        author = "James Clear",
        description = "No matter your goals, Atomic Habits offers a proven framework for improving - every day.",
        genres = listOf("Self help", "Non-fiction"))
}