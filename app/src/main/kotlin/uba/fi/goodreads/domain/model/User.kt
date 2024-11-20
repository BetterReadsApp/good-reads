package uba.fi.goodreads.domain.model

data class User (
    val isMyUser: Boolean = false,
    val name: String,
    val lastName: String,
    val email: String? = null,
    val id: Int,
    val shelves: List<Shelf>,
    val ratedBooks: List<Book>,
    val following: Int,
    val followers: Int,
    val followedByMe: Boolean = false,
    val avatarUrl: String = "",
    val isAuthor: Boolean = false
)