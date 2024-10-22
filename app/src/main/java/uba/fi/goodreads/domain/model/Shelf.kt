package uba.fi.goodreads.domain.model

import java.sql.Date
import java.time.LocalDate

data class Shelf (
    val title: String,
    val numberOfBooks: Number,
    val books: List<Book>,
    val dateAdded: LocalDate,
)
