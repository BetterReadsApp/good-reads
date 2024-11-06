package uba.fi.goodreads.domain.model

data class Book(
    val id: String,
    val title: String,
    val description: String? = null,
    val author: String,
    val pages: Int,
    val genres: List<String>? = null,
    val userRated: Int? = null,
    val publicationDate: String? = null,
    val imageUrl: String = "",
    val avgRating: Double? = null
)