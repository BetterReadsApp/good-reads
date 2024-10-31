package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.mocks.DomainShelfMocks
import uba.fi.goodreads.domain.model.User
import javax.inject.Inject

class GetShelvesUseCase @Inject constructor(
    private val shelvesRepository: ShelvesRepository,
    private val sessionRepository: SessionRepository
) {

    sealed class Result {
        data class Success(val shelves: List<Shelf>) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(): Result {
        return when (val resultWrapper = shelvesRepository.getShelves(
            sessionRepository.getAccessToken()
        )) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(resultWrapper.value)
        }
    }
}