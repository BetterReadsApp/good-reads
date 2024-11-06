package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.mocks.BookMock
import uba.fi.goodreads.domain.mocks.DomainShelfMocks
import uba.fi.goodreads.domain.model.Book
import javax.inject.Inject

class addBookToShelvesUseCase @Inject constructor(
    //private val booksRepository: BooksRepository,
    //private val sessionRepository: SessionRepository
){

    sealed class Result {
        data class Success(val book: Book, val shelves: List <Shelf>) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(bookId: String): Result {
        return Result.Success(
            book = BookMock.getbook(),
            shelves = DomainShelfMocks.getShelves()
        )
    }// TODO
}