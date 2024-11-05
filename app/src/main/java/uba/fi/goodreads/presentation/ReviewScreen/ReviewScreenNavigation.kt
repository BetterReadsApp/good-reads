package uba.fi.goodreads.presentation.ReviewScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.bookInfo.composables.BookInfoRoute

const val REVIEWSCREEN_ROUTE = "bookinfo/review"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://bookinfo/review"

fun NavController.navigateToBookInfo(bookId: String, navOptions: NavOptions? = null) = navigate(
    "bookinfo/$bookId/review", navOptions
)

fun NavGraphBuilder.bookReviewScreen() {
    composable(
        route = REVIEWSCREEN_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        //val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        BookInfoRoute()
    }
}