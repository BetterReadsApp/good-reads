package uba.fi.goodreads.domain.model

data class Book(
    val title: String,
    val description: String,
    val author: String,
    val genres: List<String>,
)