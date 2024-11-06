package uba.fi.goodreads.data.books.repositories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewBody(
    @SerialName("review") val review: String
)