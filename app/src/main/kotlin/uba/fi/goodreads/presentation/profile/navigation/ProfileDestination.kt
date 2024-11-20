package uba.fi.goodreads.presentation.profile.navigation


sealed class ProfileDestination {
    data object EditProfile: ProfileDestination()
    data object Back: ProfileDestination()
    data object AddBook: ProfileDestination() {
        val route = "profile/add_book"
    }
}