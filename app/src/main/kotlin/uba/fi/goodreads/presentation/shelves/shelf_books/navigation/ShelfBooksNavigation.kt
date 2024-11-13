package uba.fi.goodreads.presentation.shelves.shelf_books.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.shelves.shelf_books.composables.ShelfBooksRoute


const val SHELF_BOOK_ROUTE = "shelf_books/{shelfId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://shelf_books"

fun NavController.navigateToShelfBooks(shelfId: String, navOptions: NavOptions? = null) =
    navigate("shelf_books/$shelfId", navOptions)

fun NavGraphBuilder.shelfBooksScreen(
    onBack: () -> Unit,
    onBookInfo: (String) -> Unit
) {
    composable(
        route = SHELF_BOOK_ROUTE,
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
    onBookInfo: (String) -> Unit
) {
    when (destination) {
        is ShelfBooksDestination.Back -> onBack()
        is ShelfBooksDestination.BookInfo -> onBookInfo(destination.id.toString())
    }
}