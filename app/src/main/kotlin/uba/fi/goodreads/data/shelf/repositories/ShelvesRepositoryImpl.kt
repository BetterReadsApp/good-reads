package uba.fi.goodreads.data.shelf.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.data.shelf.request.CreateShelfBody
import uba.fi.goodreads.data.core.BetterReadsClient
import uba.fi.goodreads.data.core.ResponseHandler
import uba.fi.goodreads.data.shelf.request.AddBookToShelfBody
import uba.fi.goodreads.domain.model.Shelf
import javax.inject.Inject

internal class ShelvesRepositoryImpl @Inject constructor(
    private val client: BetterReadsClient,
    private val responseHandler: ResponseHandler,
): ShelvesRepository {

    override suspend fun createShelf(name: String): NetworkResult<Shelf> {
        return responseHandler {
            client.createShelf(CreateShelfBody(name = name)).toDomain()
        }
    }

    override suspend fun getShelves(userId: String): NetworkResult<List<Shelf>> {
        return responseHandler {
            client.getShelves(userId).map { it.toDomain() }
        }
    }

    override suspend fun getShelf(shelfId: String): NetworkResult<Shelf> {
        return responseHandler{
            client.getShelf(shelfId.toInt()).toDomain()
        }
    }

    override suspend fun addBook(shelfId: String, bookId: String): NetworkResult<Unit> {
        return responseHandler{
            client.addBookToShelf(shelfId, AddBookToShelfBody(bookId))
        }
    }

}