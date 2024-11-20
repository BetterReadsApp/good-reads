package uba.fi.goodreads.data.books.repositories

import kotlinx.serialization.Serializable

@Serializable
data class CreateBookBody(
    @SerialName("title"), 
)
