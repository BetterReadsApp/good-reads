package uba.fi.goodreads.domain.usecase

import androidx.annotation.Nullable
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.domain.mocks.DomainShelfMocks
import uba.fi.goodreads.domain.model.Book
import java.time.LocalDate
import javax.inject.Inject

class CreateShelfUseCase @Inject constructor(
    // private val shelvesRepository: ShelvesRepository
) {
    operator fun invoke(shelf: Shelf): Shelf {
        return shelf
    }
}