package uba.fi.goodreads.data.books.response

import uba.fi.goodreads.domain.model.UserReview


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class ReviewNetworkDto (
    @SerialName("review") val text: String,
    @SerialName("user_id") val userId: String,
) {
    fun toDomain() = UserReview(
        text = text,
        userId = userId
    )
}
