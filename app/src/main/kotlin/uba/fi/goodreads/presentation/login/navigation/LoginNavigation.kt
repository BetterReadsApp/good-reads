package uba.fi.goodreads.presentation.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.core.design_system.component.feedback.FeedbackType
import uba.fi.goodreads.presentation.login.composables.LoginRoute

const val LOGIN_ROUTE = "login"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://login"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) = navigate(LOGIN_ROUTE, navOptions)

fun NavGraphBuilder.loginScreen(
    navigateToRegister: () -> Unit
) {
    composable(
        route = LOGIN_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        LoginRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToRegister = navigateToRegister
                )
            }
        )
    }
}

internal fun navigate(
    destination: LoginDestination,
    navigateToRegister: () -> Unit,
) {
    when (destination) {
        is LoginDestination.Register -> navigateToRegister()
    }
}