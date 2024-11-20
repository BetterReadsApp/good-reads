package uba.fi.goodreads.domain.model

import uba.fi.goodreads.data.books.repositories.CreateBookBody
import uba.fi.goodreads.data.books.repositories.ReviewBody

data class AddedBook(
    val title: String,
    val summary: String,
    val genre: BookGenre,
    val pages: Int,
    val publicationDate: String,
    val coverUrl: String,
) {
    fun toDomain(authorId: String): CreateBookBody {
        return CreateBookBody(
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
