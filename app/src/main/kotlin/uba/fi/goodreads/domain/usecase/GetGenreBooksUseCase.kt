package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.Book
import javax.inject.Inject

class GetGenreBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    sealed class Result {
        data class Success(val books: List<Book>) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(text: String): Result {
        return when (val booksResult = booksRepository.getBooksByGenre(text)
        ) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> {
                Result.Success(books = booksResult.value)
            }
        }
    }
}