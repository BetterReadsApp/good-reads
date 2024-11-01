package uba.fi.goodreads.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import uba.fi.goodreads.core.ui.GoodReadsAppState
import uba.fi.goodreads.presentation.bookInfo.bookInfoScreen
import uba.fi.goodreads.presentation.bookInfo.navigateToBookInfo
import uba.fi.goodreads.presentation.home.navigation.HOME_ROUTE
import uba.fi.goodreads.presentation.home.navigation.homeScreen
import uba.fi.goodreads.presentation.profile.navigation.profileScreen
import uba.fi.goodreads.presentation.shelves.shelvesScreen.shelvesScreen

@Composable
fun GoodReadsNavHost(
    appState: GoodReadsAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(
            navigateToBook = navController::navigateToBookInfo
        )

        shelvesScreen()

        bookInfoScreen(

        )

        profileScreen(
            onBack = navController::popBackStack
        )
    }
}
