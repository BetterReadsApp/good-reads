package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
    @SerialName("status") val status: String,
    @SerialName("average_rating") val avgRating: Double,
)