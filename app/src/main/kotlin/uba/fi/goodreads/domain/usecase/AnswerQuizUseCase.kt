package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.QuizQuestion
import javax.inject.Inject

class AnswerQuizUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(
        edit: Boolean,
        quizId: String?,
        bookId: String,
        questions: List<QuizQuestion>
    ): Result {
        return when (
            if (edit) booksRepository.editQuiz(
                quizId = quizId ?: "",
                bookId = bookId,
                questions = questions
            )
            else booksRepository.createQuiz(
                bookId = bookId,
                questions = questions
            )
        ) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError

            is NetworkResult.Success -> Result.Success
        }
    }
}