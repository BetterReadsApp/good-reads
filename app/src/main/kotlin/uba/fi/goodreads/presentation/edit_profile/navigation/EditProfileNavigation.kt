package uba.fi.goodreads.presentation.edit_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.edit_profile.composables.EditProfileRoute

const val EDIT_PROFILE_ROUTE = "edit_profile"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://edit_profile"

fun NavController.navigateToEditProfile(navOptions: NavOptions? = null) = navigate(
    "edit_profile", navOptions
)

fun NavGraphBuilder.editProfileScreen(
    onBack: () -> Unit
) {
    composable(
        route = EDIT_PROFILE_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        EditProfileRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    onBack = onBack
                )
            }
        )
    }
}

internal fun navigate(
    destination: EditProfileDestination,
    onBack: () -> Unit
) {
    when (destination) {
        is EditProfileDestination.Back -> onBack()
    }
}