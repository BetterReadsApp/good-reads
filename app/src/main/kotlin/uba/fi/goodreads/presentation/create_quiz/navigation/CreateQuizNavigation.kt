package uba.fi.goodreads.presentation.create_quiz.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.create_quiz.composables.CreateQuizRoute


const val CREATE_QUIZ_ROUTE = "create_quiz/{bookId}/{quizId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://create_quiz"

fun NavController.navigateToCreateQuiz(bookId: String, quizId: String? = null, navOptions: NavOptions? = null) = navigate(
    "create_quiz/$bookId/$quizId", navOptions
)

fun NavGraphBuilder.createQuizScreen(
    onBack: () -> Unit
) {
    composable(
        route = CREATE_QUIZ_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        val quizId = navBackResult.arguments?.getString("quizId") ?: ""
        CreateQuizRoute(
            navigate = { destination ->
                navigate(
                    destination = destination,
                    onBack = onBack,
                )
            }
        )
    }
}

internal fun navigate(
    destination: CreateQuizDestination,
    onBack: () -> Unit
) {
    when (destination) {
        CreateQuizDestination.Back -> onBack()
    }
}