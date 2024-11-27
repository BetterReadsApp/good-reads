package uba.fi.goodreads.domain.model

import uba.fi.goodreads.data.books.repositories.SerializedBookBody

data class BookToSerialize(
    val title: String,
    val summary: String,
    val genre: BookGenre,
    val pages: Int,
    val publicationDate: String,
    val coverUrl: String,
) {
    fun toDomain(authorId: String): SerializedBookBody {
        return SerializedBookBody(
            title = title,
            summary = summary,
            genre = genre.name,
            pages = pages,
            publicationDate = publicationDate,
            coverImageUrl = coverUrl,
            authorId = authorId
        )
    }
}
