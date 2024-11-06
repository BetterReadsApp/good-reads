package uba.fi.goodreads.presentation.review.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.review.composables.ReviewRoute

const val REVIEWSCREEN_ROUTE = "bookinfo/{bookId}/review"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://bookinfo/review"

fun NavController.navigateToReviewScreen(bookId: String, navOptions: NavOptions? = null) = navigate(
    "bookinfo/$bookId/review", navOptions
)

fun NavGraphBuilder.reviewScreen(
    onBack: () -> Unit,
) {
    composable(
        route = REVIEWSCREEN_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        //val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        ReviewRoute(
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
    destination: ReviewDestination,
    onBack: () -> Unit,
) {
    when (destination) {
        is ReviewDestination.Back -> onBack()
    }
}