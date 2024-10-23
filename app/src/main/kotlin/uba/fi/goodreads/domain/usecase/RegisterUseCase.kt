package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {

    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
    ): Result {
        return when (sessionRepository.register(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName
        )) {
            is NetworkResult.ErrorBase,
            is NetworkResult.NetworkError,
            is NetworkResult.LocalError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success
        }
    }
}