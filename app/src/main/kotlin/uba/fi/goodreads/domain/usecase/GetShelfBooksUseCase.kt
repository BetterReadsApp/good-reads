package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.model.Book
import javax.inject.Inject

class GetShelfBooksUseCase @Inject constructor (
    private val shelvesRepository: ShelvesRepository,
    private val sessionRepository: SessionRepository,
){
    sealed class Result {
        data class Success(val books: List<Book>) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(): Result {
        return Result.Success(DomainBookMocks.getBooks())
        }
    }