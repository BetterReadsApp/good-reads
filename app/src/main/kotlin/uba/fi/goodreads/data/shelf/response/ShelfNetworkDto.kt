package uba.fi.goodreads.data.shelf.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.data.books.response.BookNetworkDto
import uba.fi.goodreads.domain.model.Shelf
import java.time.LocalDate

@Serializable
data class ShelfNetworkDto(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("books") val books: List<BookNetworkDto>? = null
) {
    fun toDomain() = Shelf(
        name = name,
        id = id,
        books = books?.map { it.toDomain() } ?: emptyList(),
        dateAdded = LocalDate.now(),
        numberOfBooks = books?.size ?: 0
    )
}
