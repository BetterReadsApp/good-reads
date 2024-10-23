package uba.fi.goodreads.presentation.bookInfo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.bookInfo.composables.BookInfoRoute


const val BOOKINFO_ROUTE = "bookinfo/{bookId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://bookinfo"

fun NavController.navigateToBookInfo(bookId: String, navOptions: NavOptions? = null) = navigate(
    "bookinfo/$bookId", navOptions
)

fun NavGraphBuilder.bookInfoScreen() {
    composable(
        route = BOOKINFO_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        BookInfoRoute()
    }
}