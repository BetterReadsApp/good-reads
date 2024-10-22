package uba.fi.goodreads.data.auth.response

import kotlinx.serialization.SerialName

data class RegisterResponse(
    @SerialName("user_id") val userId: String
)