package uba.fi.goodreads.presentation.edit_profile

import uba.fi.goodreads.presentation.edit_profile.navigation.EditProfileDestination

data class EditProfileUiState(
    val loading: Boolean = true,
    val destination: EditProfileDestination? = null,
    val firstName: String = "",
    val lastName : String = "",
    val email : String = "",
    val avatarUrl : String = "",
    val isAuthor: Boolean = false,
    val showAuthorCheck: Boolean = false,
)