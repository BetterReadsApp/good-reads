package uba.fi.goodreads.presentation.edit_book.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.edit_book.composables.EditBookRoute

const val EDIT_BOOK_ROUTE = "profile/edit_book/{bookId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads:profile/edit_book"

fun NavController.navigateToEditBook(bookId: String, navOptions: NavOptions? = null) = navigate(
    "profile/edit_book/$bookId", navOptions
)

fun NavGraphBuilder.editBookScreen(
    onBack: () -> Unit,
) {
    composable(
        route = EDIT_BOOK_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        EditBookRoute(
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
    destination: EditBookDestination,
    onBack: () -> Unit,
) {
    when (destination) {
        is EditBookDestination.Back -> onBack()
    }
}
