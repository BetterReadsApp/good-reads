package uba.fi.goodreads.data.shelf.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.domain.model.Shelf
import java.time.LocalDate

@Serializable
data class ShelfNetworkDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
) {
    fun toDomain() = Shelf(
        name = name,
        books = emptyList(),
        dateAdded = LocalDate.now(),
        numberOfBooks = 0
    )
}
