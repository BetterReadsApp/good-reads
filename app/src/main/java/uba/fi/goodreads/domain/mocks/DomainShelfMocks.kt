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
            dateAdded = LocalDate.now(),
        ),
        Shelf(
            title =  "Reading",
            numberOfBooks = 0,
            books = ArrayList<Book>(),
            dateAdded = LocalDate.now(),
        ),
        Shelf(
            title =  "Want to Read",
            numberOfBooks = 0,
            books = DomainBookMocks.getBooks(),
            dateAdded = LocalDate.now(),
        ),
    )
}