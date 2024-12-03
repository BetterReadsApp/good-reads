package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import javax.inject.Inject

class UpdateBookOnShelvesUseCase @Inject constructor(
    private val shelvesRepository: ShelvesRepository,
){

    sealed class Result {
        data object Success : Result()
        data class Error(val title: String? = null, val description: String? = null) : Result()
        data object UnexpectedError : Result()
    }

    suspend operator fun invoke(
        bookId: String,
        selectedShelves: Set<String>,
        unselectedShelves: Set<String>,
    ): Result {
        selectedShelves.forEach { shelfId ->
            shelvesRepository.addBook(shelfId, bookId)
        }
        unselectedShelves.forEach { shelfId ->
            shelvesRepository.deleteBook(shelfId, bookId)
        }
        return Result.Success
    }
}