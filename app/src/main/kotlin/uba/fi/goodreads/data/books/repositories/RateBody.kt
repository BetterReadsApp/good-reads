package uba.fi.goodreads.data.books.repositories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateBody(
    @SerialName("value") val value: Int
)