package uba.fi.goodreads.presentation.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.edit_profile.navigation.EditProfileDestination
import uba.fi.goodreads.presentation.profile.composables.ProfileRoute

const val PROFILE_ROUTE = "profile/{userId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://profile"

fun NavController.navigateToProfile(userId: String? = null, navOptions: NavOptions? = null) = navigate(
    "profile/$userId", navOptions
)

fun NavGraphBuilder.profileScreen(
    navigateToEditProfile: () -> Unit,
    onBack: () -> Unit
) {
    composable(
        route = PROFILE_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) { navBackResult ->
        val userId = navBackResult.arguments?.getString("userId") ?: ""
        ProfileRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToEditProfile = navigateToEditProfile,
                    onBack = onBack
                )
            }
        )
    }
}

internal fun navigate(
    destination: ProfileDestination,
    navigateToEditProfile: () -> Unit,
    onBack: () -> Unit
) {
    when (destination) {
        is ProfileDestination.EditProfile -> navigateToEditProfile()
        is ProfileDestination.Back -> onBack()
    }
}