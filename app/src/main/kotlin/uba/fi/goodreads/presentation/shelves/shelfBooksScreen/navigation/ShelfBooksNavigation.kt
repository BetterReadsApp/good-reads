package uba.fi.goodreads.presentation.shelves.shelfBooksScreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.shelves.shelfBooksScreen.composables.ShelfBooksRoute


const val SHELVES_ROUTE = "my_books"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://shelves"

fun NavController.navigateToShelves(navOptions: NavOptions) = navigate(SHELVES_ROUTE, navOptions)

fun NavGraphBuilder.shelfBooksScreen(
    onBack: () -> Unit,
    onBookInfo: () -> Unit
) {
    composable(
        route = SHELVES_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        ShelfBooksRoute(
            navigate = { destination ->
            navigate(
                destination = destination,
                onBack = onBack,
                onBookInfo = onBookInfo
            )
        }
        )
    }
}

internal fun navigate(
    destination: ShelfBooksDestination,
    onBack: () -> Unit,
    onBookInfo: () -> Unit
) {
    when (destination) {
        is ShelfBooksDestination.Back -> onBack()
        is ShelfBooksDestination.BookInfo -> onBookInfo()
    }
}