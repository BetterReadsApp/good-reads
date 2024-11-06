package uba.fi.goodreads.domain.model

import java.time.LocalDate

data class Shelf (
    val name: String,
    val id: Int,
    val numberOfBooks: Number,
    val books: List<Book>,
    val dateAdded: LocalDate,
)
