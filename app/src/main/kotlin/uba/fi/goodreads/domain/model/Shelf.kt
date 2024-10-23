package uba.fi.goodreads.domain.model

import java.time.LocalDate

data class Shelf (
    val name: String,
    val numberOfBooks: Number,
    val books: List<Book>,
    val dateAdded: LocalDate,
)
