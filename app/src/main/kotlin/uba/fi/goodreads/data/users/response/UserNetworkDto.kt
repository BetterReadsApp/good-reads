package uba.fi.goodreads.data.users.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.data.books.response.BookNetworkDto
import uba.fi.goodreads.data.books.response.RatedBookDto
import uba.fi.goodreads.data.shelf.response.ShelfNetworkDto
import uba.fi.goodreads.domain.model.User

@Serializable
data class UserNetworkDto(
    @SerialName("name") val name: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String? = null,
    @SerialName("id") val id: Int,
    @SerialName("shelves") val shelves: List<ShelfNetworkDto>? = null,
    @SerialName("rated_books") val ratedBooks: List<RatedBookDto>? = null,
    @SerialName("following") val following: List<UserNetworkDto>? = null,
    @SerialName("followers") val followers: List<UserNetworkDto>? = null,
    @SerialName("is_following") val isFollowing: Boolean? = null,
) {
    fun toDomain(ownId: String? = null) = User(
        isMyUser = ownId == id.toString(),
        name = name,
        lastName = lastName,
        email = email,
        id = id,
        shelves = shelves?.map { it.toDomain() } ?: emptyList(),
        ratedBooks = ratedBooks?.map { it.toDomain() } ?: emptyList(),
        following = following?.size ?: 0,
        followers = followers?.size ?: 0,
        followedByMe = isFollowing ?: false
    )
}