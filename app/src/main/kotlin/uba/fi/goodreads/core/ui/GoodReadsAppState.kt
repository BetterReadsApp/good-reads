package uba.fi.goodreads.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import uba.fi.goodreads.core.navigation.TopLevelDestination
import uba.fi.goodreads.core.navigation.TopLevelDestination.DISCOVER
import uba.fi.goodreads.core.navigation.TopLevelDestination.HOME
import uba.fi.goodreads.core.navigation.TopLevelDestination.PROFILE
import uba.fi.goodreads.core.navigation.TopLevelDestination.MY_BOOKS
import uba.fi.goodreads.presentation.home.navigation.HOME_ROUTE
import uba.fi.goodreads.presentation.home.navigation.navigateToHome
import uba.fi.goodreads.presentation.profile.navigation.navigateToProfile
import uba.fi.goodreads.presentation.shelves.navigateToShelves

@Composable
fun rememberGoodReadsAppState(
    isSignedIn: Boolean,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): GoodReadsAppState {
    return remember(
        navController,
        coroutineScope,
        isSignedIn,
    ) {
        GoodReadsAppState(
            isSignedIn = isSignedIn,
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class GoodReadsAppState(
    val isSignedIn: Boolean,
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> HOME
            // MY_BOOKS_ROUTE -> TopLevelDestination.MY_BOOKS
            // DISCOVER_ROUTE -> TopLevelDestination.DISCOVER
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = isSignedIn

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                HOME -> navController.navigateToHome(topLevelNavOptions)
                MY_BOOKS -> navController.navigateToShelves(topLevelNavOptions)
                DISCOVER -> navController.navigateToHome(topLevelNavOptions) //TODO
                PROFILE -> navController.navigateToProfile(topLevelNavOptions) //TODO
            }
        }
    }
}
