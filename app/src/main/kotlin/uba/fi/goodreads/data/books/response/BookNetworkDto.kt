package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.domain.model.Book

@Serializable
data class BookNetworkDto(
    @SerialName("pages") val pages: Int,
    @SerialName("title") val title: String,
    @SerialName("id") val id: Int,
    @SerialName("summary") val summary: String,
    @SerialName("author") val author: String,
    @SerialName("publication_date") val publicationDate: String,
) {
    fun toDomain() = Book(
        title = title,
        author = author,
        description = summary,
        genres = listOf("Fiction"),
        publicationDate = publicationDate,
    )
}
