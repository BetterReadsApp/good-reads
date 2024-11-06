package uba.fi.goodreads.presentation.bookInfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.bookInfo.composables.BookInfoRoute
import uba.fi.goodreads.presentation.home.navigation.HomeDestination


const val BOOKINFO_ROUTE = "bookinfo/{bookId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://bookinfo"

fun NavController.navigateToBookInfo(bookId: String, navOptions: NavOptions? = null) = navigate(
    "bookinfo/$bookId", navOptions
)

fun NavGraphBuilder.bookInfoScreen(
    navigateToReview: (String) -> Unit
) {
    composable(
        route = BOOKINFO_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        BookInfoRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToReview = navigateToReview
                )
            }
        )
    }
}

internal fun navigate(
    destination: BookInfoDestination,
    navigateToReview: (String) -> Unit,
) {
    when (destination) {
        is BookInfoDestination.Review -> navigateToReview(destination.bookId)
    }
}