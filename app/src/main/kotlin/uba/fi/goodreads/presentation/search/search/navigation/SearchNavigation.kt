package uba.fi.goodreads.presentation.search.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.search.search.composables.SearchRoute

const val SEARCH_ROUTE = "search"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://search"

fun NavController.navigateToSearch(navOptions: NavOptions) = navigate(SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen(
    navigateToBook: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToGenre: (String) -> Unit
) {
    composable(
        route = SEARCH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        SearchRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToBook = navigateToBook,
                    navigateToProfile = navigateToProfile,
                    navigateToGenre = navigateToGenre
                )
            }
        )
    }
}

internal fun navigate(
    destination: SearchDestination,
    navigateToBook: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToGenre: (String) -> Unit
) {
    when (destination) {
        is SearchDestination.BookInfo -> navigateToBook(destination.id)
        is SearchDestination.Profile -> navigateToProfile(destination.id.toString())
        is SearchDestination.Genre -> navigateToGenre(destination.genre)
    }
}