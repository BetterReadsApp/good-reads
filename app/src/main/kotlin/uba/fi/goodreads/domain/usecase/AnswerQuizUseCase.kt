package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.QuizAnswer
import javax.inject.Inject

class AnswerQuizUseCase @Inject constructor(
    private val booksRepository: BooksRepository,
) {
    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(
        quizId: String,
        answers: List<QuizAnswer>
    ): Result {
        return when (
            val resultWrapper = booksRepository.answerQuiz(quizId, answers)
        ) {
            is NetworkResult.GenericError -> Result.Error(resultWrapper.title, resultWrapper.detail)
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError

            is NetworkResult.Success -> Result.Success
        }
    }
}