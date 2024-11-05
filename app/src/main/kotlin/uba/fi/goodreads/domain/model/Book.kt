package uba.fi.goodreads.domain.model

data class Book(
    val title: String,
    val description: String,
    val author: String,
    val pages: Int,
    val genres: List<String>,
    val publicationDate: String,
    val id: Int,
    val avgRating: Double? = null
)