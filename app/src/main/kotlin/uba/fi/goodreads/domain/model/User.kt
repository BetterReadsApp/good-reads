package uba.fi.goodreads.domain.model

data class User (
    val name: String,
    val lastName: String,
    val email: String,
    val id: Int,
    val shelves: List<Shelf>,
    val ratedBooks: List<Book>,
    val following: Int,
    val followers: Int,
)