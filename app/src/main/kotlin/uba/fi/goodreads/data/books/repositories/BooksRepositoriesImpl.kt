package uba.fi.goodreads.data.books.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.books.request.QuizDto
import uba.fi.goodreads.data.core.BetterReadsClient
import uba.fi.goodreads.data.core.ResponseHandler
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.QuizQuestion
import javax.inject.Inject

internal class BooksRepositoriesImpl @Inject constructor(
    private val client: BetterReadsClient,
    private val responseHandler: ResponseHandler
) : BooksRepository {

    override suspend fun getBook(bookId: String, currentUserId: String): NetworkResult<Book> {
        return responseHandler {
            client.getBook(bookId).toDomain(currentUserId)
        }
    }

    override suspend fun getBooksByKeyword(text: String): NetworkResult<List<Book>> {
        return responseHandler {
            client.getBooksByKeyword(text).map { it.toDomain() }
        }
    }

    override suspend fun getBooksByGenre(genre: String): NetworkResult<List<Book>> {
        return responseHandler {
            client.getBooksByGenre(genre).map { it.toDomain() }
        }
    }

    override suspend fun rateBook(
        bookId: String,
        userId: String,
        rate: Int
    ): NetworkResult<Double> {
        return responseHandler {
            client.rateBook(bookId, userId, RateBody(value = rate)).avgRating
        }
    }

    override suspend fun reviewBook(
        bookId: String,
        userId: String,
        review: String
    ): NetworkResult<String> {
        return responseHandler {
            client.reviewBook(bookId, userId, ReviewBody(review = review)).your_new_description
        }
    }

    override suspend fun editQuiz(
        quizId: String,
        bookId: String,
        questions: List<QuizQuestion>
    ): NetworkResult<Unit> {
        return responseHandler {
            client.editQuiz(
                quizId = quizId,
                body = QuizDto(
                    title = "Quiz",
                    bookId = bookId,
                    questions = questions.map { it.toDto() }
                ))
        }
    }

    override suspend fun createQuiz(
        bookId: String,
        questions: List<QuizQuestion>
    ): NetworkResult<Unit> {
        return responseHandler {
            client.createQuiz(
                body = QuizDto(
                    title = "Quiz",
                    bookId = bookId,
                    questions = questions.map { it.toDto() }
                ))
        }
    }

    override suspend fun getQuiz(quizId: String): NetworkResult<List<QuizQuestion>> {
        return responseHandler {
            client.getQuiz(quizId = quizId).map { it.toDomain() }
        }
    }

    override suspend fun getRecommendedBooks(userId: String): NetworkResult<List<Book>> {
        return responseHandler {
            client.getRecommendedBooks().map { it.toDomain() }
        }
    }
}