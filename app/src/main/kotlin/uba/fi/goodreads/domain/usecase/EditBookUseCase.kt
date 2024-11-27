package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.BookToSerialize
import javax.inject.Inject


class EditBookUseCase @Inject constructor(
    private val bookRepository: BooksRepository,
    private val sessionRepository: SessionRepository
) {
    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }
    suspend operator fun invoke(bookId: String, book: BookToSerialize): Result {
        return when(val result = bookRepository.editBook(bookId, book, sessionRepository.getUserId())) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> EditBookUseCase.Result.UnexpectedError
            is NetworkResult.Success -> EditBookUseCase.Result.Success
        }
    }
}