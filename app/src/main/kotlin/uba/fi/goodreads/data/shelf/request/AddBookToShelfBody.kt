package uba.fi.goodreads.data.shelf.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddBookToShelfBody(
    @SerialName("book_id") val name: String
)
