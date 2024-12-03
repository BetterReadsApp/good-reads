package uba.fi.goodreads.data.shelf.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.Shelf

interface ShelvesRepository {

    suspend fun createShelf(name: String): NetworkResult<Shelf>

    suspend fun getShelves(userId: String): NetworkResult<List<Shelf>>

    suspend fun getShelf(shelfId: String): NetworkResult<Shelf>

    suspend fun renameShelf(shelfId: String, name: String): NetworkResult<Shelf>

    suspend fun deleteShelf(shelfId: String): NetworkResult<Unit>

    suspend fun addBook(shelfId: String, bookId: String): NetworkResult<Unit>

    suspend fun deleteBook(shelfId: String, bookId: String): NetworkResult<Unit>
}