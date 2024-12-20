package uba.fi.goodreads.presentation.profile

import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.domain.model.Shelf
import uba.fi.goodreads.presentation.edit_profile.navigation.EditProfileDestination
import uba.fi.goodreads.presentation.profile.navigation.ProfileDestination

data class ProfileUiState(
    val destination: ProfileDestination? = null,
    val loading: Boolean = true,
    val avatarUrl: String = "",
    val firstName: String = "",
    val lastName : String = "",
    val followingAmount: Int = 0,
    val followersAmount: Int = 0,
    val shelves: List<Shelf> = emptyList(),
    val ratedBooks: List<Book> = emptyList(),
    val followedByMe: Boolean = false,
    val ownProfile: Boolean = true,
    val isAuthor: Boolean = false,
)