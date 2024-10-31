package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import uba.fi.goodreads.domain.model.Shelf
import javax.inject.Inject

class ReviewBookUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    sealed class Result {
        data class Success(val avgRating: Double) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(bookId: String, rating: Int): Result {
        return when(val result = booksRepository.rateBook(
            bookId, rating
        )) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(result.value)
        }
    }

}