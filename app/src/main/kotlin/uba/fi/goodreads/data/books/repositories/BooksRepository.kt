package uba.fi.goodreads.data.books.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.Book

interface BooksRepository {

    suspend fun getBook(bookId: String): NetworkResult<Book>

    suspend fun getBooks(text: String): NetworkResult<List<Book>>

    suspend fun rateBook(bookId: String, userId: String, rate: Int): NetworkResult<Double>

    suspend fun reviewBook(bookId: String, userId: String ,review: String): NetworkResult<String>
}