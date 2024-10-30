package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.data.users.repositories.UsersRepository
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.User
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val sessionRepository: SessionRepository,
){

    sealed class Result {
        data class Success(val user: User) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(): Result {
        return when (val resultWrapper = usersRepository.getUser(sessionRepository.getAccessToken())) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(resultWrapper.value)
        }
    }
}