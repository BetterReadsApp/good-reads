package uba.fi.goodreads.data.shelf.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.Shelf

interface ShelvesRepository {

    suspend fun createShelf(name: String): NetworkResult<Shelf>

    suspend fun getShelves(userId: String): NetworkResult<List<Shelf>>

    suspend fun getShelf(shelfId: String): NetworkResult<Shelf>

    suspend fun addBook(shelfId: String, bookId: String): NetworkResult<Unit>
}