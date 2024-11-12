package uba.fi.goodreads.data.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("is_author") val isAuthor: Boolean,
)