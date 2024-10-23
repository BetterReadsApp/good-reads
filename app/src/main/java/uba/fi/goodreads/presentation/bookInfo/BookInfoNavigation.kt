package uba.fi.goodreads.presentation.bookInfo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.bookInfo.composables.BookInfoRoute


const val BOOKINFO_ROUTE = "bookinfo"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://bookinfo"

fun NavController.navigateToBookInfo(navOptions: NavOptions) = navigate(BOOKINFO_ROUTE, navOptions)

fun NavGraphBuilder.bookInfoScreen() {
    composable(
        route = BOOKINFO_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) {
        BookInfoRoute()
    }
}