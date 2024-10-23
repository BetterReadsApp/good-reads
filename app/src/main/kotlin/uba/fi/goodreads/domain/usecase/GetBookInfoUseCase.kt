package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.domain.mocks.BookMock
import javax.inject.Inject

class GetBookInfoUseCase @Inject constructor(
){
    operator fun invoke(): uba.fi.goodreads.domain.model.Book {
        return BookMock.getbook()
    }
}