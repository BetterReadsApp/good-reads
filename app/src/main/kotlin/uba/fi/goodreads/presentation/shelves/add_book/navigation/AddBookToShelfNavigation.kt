package uba.fi.goodreads.presentation.shelves.add_book.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.shelves.add_book.composables.AddToShelvesRoute


const val ADD_BOOK_ROUTE = "add_book/{bookId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://add_book"

fun NavController.navigateToAddBookToShelves(bookId: String, navOptions: NavOptions? = null) =
    navigate("add_book/$bookId", navOptions)

fun NavGraphBuilder.addBookToShelvesScreen(
    onBack: () -> Unit,
) {
    composable(
        route = ADD_BOOK_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        AddToShelvesRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    onBack = onBack,
                )
            }
        )
    }
}

internal fun navigate(
    destination: AddBookToShelfDestination,
    onBack: () -> Unit,
) {
    when (destination) {
        is AddBookToShelfDestination.Back -> onBack()
    }
}