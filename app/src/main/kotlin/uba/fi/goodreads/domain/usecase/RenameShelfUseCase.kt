package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import uba.fi.goodreads.domain.model.Shelf
import javax.inject.Inject

class RenameShelfUseCase @Inject constructor(
    private val shelvesRepository: ShelvesRepository
) {
    sealed class Result {
        data class Success(val shelf: Shelf) : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(shelfId:String, name: String): Result {
        return when(val resultWrapper = shelvesRepository.renameShelf(shelfId = shelfId,name = name)) {
            is NetworkResult.ErrorBase,
            is NetworkResult.LocalError,
            is NetworkResult.NetworkError -> Result.UnexpectedError
            is NetworkResult.Success -> Result.Success(resultWrapper.value)
        }
    }
}