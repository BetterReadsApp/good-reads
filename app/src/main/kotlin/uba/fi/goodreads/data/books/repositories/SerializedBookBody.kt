package uba.fi.goodreads.data.books.repositories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerializedBookBody(
    @SerialName("title") val title: String,
    @SerialName("summary") val summary: String,
    @SerialName("genre") val genre: String,
    @SerialName("pages") val pages: Int,
    @SerialName("publication_date") val publicationDate: String,
    @SerialName("cover_image_url") val coverImageUrl: String,
    @SerialName("author_id") val authorId: String? = null
)