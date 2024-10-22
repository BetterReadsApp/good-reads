package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.model.Book
import java.time.LocalDate


object DomainShelfMocks {
    fun getShelves() = listOf(
        Shelf(
            title =  "Read",
            numberOfBooks = 0,
            books = ArrayList<Book>(),
            dateAdded = LocalDate.of(2024,10,18)
        ),
        Shelf(
            title =  "Reading",
            numberOfBooks = 0,
            books = ArrayList<Book>(),
            dateAdded = LocalDate.of(2024,10,18),
        ),
        Shelf(
            title =  "Want to Read",
            books = DomainBookMocks.getBooks(),
            numberOfBooks = DomainBookMocks.getBooks().count(),
            dateAdded = LocalDate.now(),
        ),
    )
}