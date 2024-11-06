package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.domain.model.Book

@Serializable
data class BookNetworkDto(
    @SerialName("pages") val pages: Int? = null,
    @SerialName("title") val title: String,
    @SerialName("id") val id: Int,
    @SerialName("summary") val summary: String? = null,
    @SerialName("author") val author: String,
    @SerialName("genre") val genre: String? = null,
    @SerialName("publication_date") val publicationDate: String? = null,
    @SerialName("average_rating") val avgRating: Double? = null,
) {
    fun toDomain() = Book(
        id = id.toString(),
        title = title,
        author = author,
        pages = pages ?: 0,
        description = summary ?: "",
        genres = genre?.let { listOf(it) } ?: emptyList(),
        publicationDate = publicationDate ?: "",
        avgRating = avgRating,
        userRated = null
    )
}

@Serializable
data class RatedBookDto(
    @SerialName("book_id") val id: Int,
    @SerialName("value") val rating: Int,
) {
    fun toDomain() = Book(
        id = id.toString(),
        title = "Hardcoded",
        author = "Author hardcoded",
        userRated = rating,
        pages = 0
    )
}
