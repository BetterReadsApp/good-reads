package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.BookToSerialize
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BooksRepository,
) {
    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(bookId: String): Result {
        return when(val result = bookRepository.deleteBook(bookId)) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> DeleteBookUseCase.Result.UnexpectedError
            is NetworkResult.Success -> DeleteBookUseCase.Result.Success
        }
    }
}