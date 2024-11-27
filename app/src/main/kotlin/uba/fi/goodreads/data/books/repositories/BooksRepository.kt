package uba.fi.goodreads.data.books.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.BookToSerialize
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.QuizAnswer
import uba.fi.goodreads.domain.model.QuizQuestion

interface BooksRepository {

    suspend fun getBook(bookId: String, currentUserId: String): NetworkResult<Book>

    suspend fun getBooksByKeyword(text: String): NetworkResult<List<Book>>

    suspend fun getBooksByGenre(genre:String): NetworkResult<List<Book>>

    suspend fun rateBook(bookId: String, userId: String, rate: Int): NetworkResult<Double>

    suspend fun reviewBook(bookId: String, userId: String, review: String): NetworkResult<String>

    suspend fun createQuiz(
        bookId: String,
        questions: List<QuizQuestion>
    ): NetworkResult<Unit>

    suspend fun editQuiz(
        quizId: String,
        bookId: String,
        questions: List<QuizQuestion>
    ): NetworkResult<Unit>

    suspend fun answerQuiz(
        quizId: String,
        answers: List<QuizAnswer>
    ): NetworkResult<Unit>

    suspend fun getQuiz(quizId: String): NetworkResult<List<QuizQuestion>>

    suspend fun getRecommendedBooks(userId: String): NetworkResult<List<Book>>

    suspend fun createBook(book: BookToSerialize, authorId: String): NetworkResult<Unit>

    suspend fun editBook(book_id: String, book: BookToSerialize, authorId: String): NetworkResult<Unit>

    suspend fun deleteBook(book_id: String): NetworkResult<Unit>

}