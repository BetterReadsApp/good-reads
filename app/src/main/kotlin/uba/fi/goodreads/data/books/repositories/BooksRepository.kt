package uba.fi.goodreads.data.books.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.Book

interface BooksRepository {

    suspend fun getBook(bookId: String): NetworkResult<Book>

    suspend fun rateBook(bookId: String, rate: Int): NetworkResult<Double>

}