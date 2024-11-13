package uba.fi.goodreads.presentation.search.genreScreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.search.searchScreen.composables.GenreRoute
import uba.fi.goodreads.presentation.search.searchScreen.composables.SearchRoute
import uba.fi.goodreads.presentation.search.searchScreen.navigation.GenreDestination

const val GENRE_ROUTE = "genre"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://genre"

fun NavController.navigateToGenre(genre: String? = null, navOptions: NavOptions? = null) = navigate(
    "genre/$genre", navOptions
)

fun NavGraphBuilder.genreScreen(
    navigateToBook: (String) -> Unit
) {
    composable(
        route = GENRE_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        GenreRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToBook = navigateToBook
                )
            }
        )
    }
}

internal fun navigate(
    destination: GenreDestination,
    navigateToBook: (String) -> Unit
) {
    when (destination) {
        is GenreDestination.BookInfo -> navigateToBook(destination.id)
        else -> {}
    }
}