package uba.fi.goodreads.domain.model

import uba.fi.goodreads.data.books.response.QuizQuestionDto
import java.time.LocalDate

data class Shelf (
    val name: String,
    val id: Int,
    val numberOfBooks: Number,
    val books: List<Book>,
    val dateAdded: LocalDate,
)
{
    fun containsBook(bookName: String): Boolean {
        books.forEach{book: Book ->
            if (book.title == bookName) {
                return true
            }
        }
        return false
    }

}

