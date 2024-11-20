package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.AddedBook
import uba.fi.goodreads.domain.usecase.RateBookUseCase.Result
import javax.inject.Inject


class SaveBookUseCase @Inject constructor(
    private val bookRepository: BooksRepository,
    private val sessionRepository: SessionRepository
) {
    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }
    suspend operator fun invoke(book: AddedBook): Result {
        return when(val result = bookRepository.createBook(
            book, sessionRepository.getUserId()
        )) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> SaveBookUseCase.Result.UnexpectedError
            is NetworkResult.Success -> SaveBookUseCase.Result.Success
        }
    }
}