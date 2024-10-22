package uba.fi.goodreads.data.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)