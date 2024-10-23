package uba.fi.goodreads.data.shelf.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateShelfBody(
    @SerialName("name") val name: String
)
