package uba.fi.goodreads.presentation.shelves.shelvesScreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.profile.navigation.ProfileDestination
import uba.fi.goodreads.presentation.shelves.shelvesScreen.composables.ShelvesRoute


const val SHELVES_ROUTE = "my_books"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://my_books"

fun NavController.navigateToShelves(navOptions: NavOptions) = navigate(SHELVES_ROUTE, navOptions)

fun NavGraphBuilder.shelvesScreen(
    navigateToShelfBooks: (String) -> Unit
) {
    composable(
        route = SHELVES_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        ShelvesRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToShelfBooks = navigateToShelfBooks
                )
            }
        )
    }
}

internal fun navigate(
    destination: ShelvesDestination,
    navigateToShelfBooks: (String) -> Unit
) {
    when (destination) {
        is ShelvesDestination.ShelfBooks -> navigateToShelfBooks(destination.shelfId.toString())
    }
}