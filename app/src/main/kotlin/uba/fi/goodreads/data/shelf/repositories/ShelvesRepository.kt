package uba.fi.goodreads.data.shelf.repositories

import uba.fi.goodreads.core.network.NetworkResult
import uba.fi.goodreads.domain.model.Shelf

interface ShelvesRepository {

    suspend fun createShelf(name: String): NetworkResult<Shelf>
}