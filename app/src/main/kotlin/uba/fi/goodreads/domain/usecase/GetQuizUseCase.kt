package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.QuizQuestion
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    sealed class Result {
        data class Success(val quiz: List<QuizQuestion>) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(bookId: String): Result {
        return when(val resultWrapper = booksRepository.getQuiz(bookId = bookId)) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(resultWrapper.value)
        }
    }
}