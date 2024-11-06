package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.model.Book
import java.time.LocalDate


object DomainShelfMocks {
    fun getShelves() = listOf(
        Shelf(
            name =  "Read",
            numberOfBooks = 0,
            id = 1,
            books = ArrayList<Book>(),
            dateAdded = LocalDate.of(2024,10,18)
        ),
        Shelf(
            name =  "Reading",
            numberOfBooks = 0,
            id = 2,
            books = ArrayList<Book>(),
            dateAdded = LocalDate.of(2024,10,18),
        ),
        Shelf(
            name =  "Want to Read",
            books = DomainBookMocks.getBooks(),
            id = 3,
            numberOfBooks = DomainBookMocks.getBooks().count(),
            dateAdded = LocalDate.now(),
        ),
    )
}