package uba.fi.goodreads.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uba.fi.goodreads.core.ui.GoodReadsAppState
import uba.fi.goodreads.presentation.home.HOME_ROUTE
import uba.fi.goodreads.presentation.home.homeScreen
import uba.fi.goodreads.presentation.shelves.shelvesScreen

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
        homeScreen()

        shelvesScreen()
    }
}
