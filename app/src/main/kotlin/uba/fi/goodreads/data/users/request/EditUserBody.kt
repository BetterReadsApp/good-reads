package uba.fi.goodreads.data.users.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditUserBody(
    @SerialName("name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("is_author") val isAuthor: Boolean,
    @SerialName("avatar_image_url") val avatarUrl: String,
)
