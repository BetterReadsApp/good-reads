package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.mocks.DomainShelfMocks
import javax.inject.Inject

class GetShelvesUseCase @Inject constructor(
    // private val shelvesRepository: ShelvesRepository
) {
    operator fun invoke(): List<Shelf> {
        return DomainShelfMocks.getShelves()
    }
}