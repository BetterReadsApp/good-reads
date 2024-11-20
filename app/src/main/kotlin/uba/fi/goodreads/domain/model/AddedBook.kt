package uba.fi.goodreads.domain.model

data class AddedBook(
    val title: String,
    val summary: String,
    val genre: BookGenre,
    val pages: Int,
    val publicationDate: String,
    val coverUrl: String,
    val authorId: Int
)
