package uba.fi.goodreads.presentation.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.profile.composables.ProfileRoute

const val PROFILE_ROUTE = "profile"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://profile"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) = navigate(PROFILE_ROUTE, navOptions)

fun NavGraphBuilder.profileScreen(
    onBack: () -> Unit
) {
    composable(
        route = PROFILE_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        ProfileRoute(
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
    destination: ProfileDestination,
    onBack: () -> Unit
) {
    when (destination) {
        is ProfileDestination.Back -> onBack()
    }
}