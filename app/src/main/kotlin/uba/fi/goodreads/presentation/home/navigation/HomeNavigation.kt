package uba.fi.goodreads.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.home.composables.HomeRoute
import uba.fi.goodreads.presentation.search.navigation.SearchDestination

const val HOME_ROUTE = "home"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://home"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(
    navigateToBook: (String) -> Unit
) {
    composable(
        route = HOME_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        HomeRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToBook = navigateToBook
                )
            }
        )
    }
}

internal fun navigate(
    destination: HomeDestination,
    navigateToBook: (String) -> Unit,
) {
    when (destination) {
        is HomeDestination.BookInfo -> navigateToBook(destination.id.toString())
    }
}