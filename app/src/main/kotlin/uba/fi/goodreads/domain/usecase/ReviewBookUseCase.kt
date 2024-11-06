package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import javax.inject.Inject

class ReviewBookUseCase @Inject constructor(
    private val booksRepository: BooksRepository,
    private val sessionRepository: SessionRepository
) {
    sealed class Result {
        data class Success(val newReview: String) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(bookId: String, review: String): Result {
        return when(val result = booksRepository.reviewBook(
            bookId, sessionRepository.getAccessToken(), review
        )) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(result.value)
        }
    }

}