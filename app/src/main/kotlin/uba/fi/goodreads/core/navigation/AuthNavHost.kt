package uba.fi.goodreads.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import uba.fi.goodreads.core.ui.GoodReadsAppState
import uba.fi.goodreads.presentation.login.navigation.LOGIN_ROUTE
import uba.fi.goodreads.presentation.login.navigation.loginScreen
import uba.fi.goodreads.presentation.login.navigation.navigateToLogin
import uba.fi.goodreads.presentation.register.navigation.navigateToRegister
import uba.fi.goodreads.presentation.register.navigation.registerScreen

@Composable
fun AuthNavHost(
    appState: GoodReadsAppState,
    modifier: Modifier = Modifier,
    startDestination: String = LOGIN_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        loginScreen(
            navigateToRegister = navController::navigateToRegister
        )

        registerScreen(
            navigateToLogin = navController::navigateToLogin
        )
    }
}