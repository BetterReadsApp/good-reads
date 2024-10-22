package uba.fi.goodreads.presentation.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.home.composables.HomeRoute
import uba.fi.goodreads.presentation.login.composables.LoginRoute

const val LOGIN_ROUTE = "login"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://login"

fun NavController.navigateToLogin(navOptions: NavOptions) = navigate(LOGIN_ROUTE, navOptions)

fun NavGraphBuilder.loginScreen() {
    composable(
        route = LOGIN_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        LoginRoute()
    }
}