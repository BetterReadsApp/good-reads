package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.domain.model.BookAuthor

@Serializable
class AuthorDto (
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String,
    @SerialName("last_name") val lastName: String,
    ) {
    fun toDomain() = BookAuthor(
        id = id.toString(),
        name = name,
        lastName = lastName
    )
}
