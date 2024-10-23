package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.domain.mocks.DomainBookMocks
import uba.fi.goodreads.domain.model.Book
import javax.inject.Inject

class GetForYouUseCase @Inject constructor(
    // private val booksRepository: BooksRepository
) {
    operator fun invoke(): List<Book> {
        return DomainBookMocks.getBooks()
    }
}