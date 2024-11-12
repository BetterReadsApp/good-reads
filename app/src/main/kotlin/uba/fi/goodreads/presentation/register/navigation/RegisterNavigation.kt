package uba.fi.goodreads.presentation.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.register.composables.RegisterRoute

const val REGISTER_ROUTE = "register"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://register"

fun NavController.navigateToRegister(navOptions: NavOptions? = null) = navigate(REGISTER_ROUTE, navOptions)

fun NavGraphBuilder.registerScreen(
    navigateToLogin: () -> Unit,
) {
    composable(
        route = REGISTER_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        RegisterRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    navigateToLogin = navigateToLogin
                )
            }
        )
    }
}

internal fun navigate(
    destination: RegisterDestination,
    navigateToLogin: () -> Unit,
) {
    when (destination) {
        is RegisterDestination.Login -> navigateToLogin()
    }
}