package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.domain.model.AddedBook
import javax.inject.Inject


class saveBookUseCase @Inject constructor(
    private val bookRepository: BooksRepository,
    private val sessionRepository: SessionRepository
    ) {

    suspend operator fun invoke(book: AddedBook) {
        bookRepository.add(book)
    }
}