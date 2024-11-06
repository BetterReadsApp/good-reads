package uba.fi.goodreads.data.books.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.core.BetterReadsClient
import uba.fi.goodreads.data.core.ResponseHandler
import uba.fi.goodreads.domain.model.Book
import javax.inject.Inject

internal class BooksRepositoriesImpl @Inject constructor(
    private val client: BetterReadsClient,
    private val responseHandler: ResponseHandler
) : BooksRepository {

    override suspend fun getBook(bookId: String): NetworkResult<Book> {
        return responseHandler {
            client.getBook(bookId).toDomain()
        }
    }

    override suspend fun getBooks(text: String): NetworkResult<List<Book>> {
        return responseHandler {
            client.getBooks(text).map { it.toDomain() }
        }
    }

    override suspend fun rateBook(bookId: String, rate: Int): NetworkResult<Double> {
        return responseHandler {
            client.rateBook(bookId, rate).avgRating
        }
    }
}