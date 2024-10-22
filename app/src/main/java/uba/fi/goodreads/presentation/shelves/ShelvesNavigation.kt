package uba.fi.goodreads.presentation.shelves

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.shelves.composables.ShelvesRoute


const val SHELVES_ROUTE = "shelves"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://shelves"

fun NavController.navigateToShelves(navOptions: NavOptions) = navigate(SHELVES_ROUTE, navOptions)

fun NavGraphBuilder.shelvesScreen() {
    composable(
        route = SHELVES_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        ShelvesRoute()
    }
}