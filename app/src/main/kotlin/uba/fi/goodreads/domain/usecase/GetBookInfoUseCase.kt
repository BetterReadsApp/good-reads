package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.Book
import javax.inject.Inject

class GetBookInfoUseCase @Inject constructor(
    private val booksRepository: BooksRepository
){

    sealed class Result {
        data class Success(val book: Book) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(bookId: String): Result {
        return when (val resultWrapper = booksRepository.getBook(bookId)) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(resultWrapper.value)
        }
    }
}