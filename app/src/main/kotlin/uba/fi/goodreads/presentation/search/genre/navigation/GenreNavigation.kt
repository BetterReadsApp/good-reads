package uba.fi.goodreads.presentation.search.genre.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.search.genre.composables.GenreRoute
import uba.fi.goodreads.presentation.search.search.navigation.GenreDestination

const val GENRE_ROUTE = "genre/{genre}"
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
    ) { navBackStackEntry ->
        val genre = navBackStackEntry.arguments?.getString("genre")
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