package uba.fi.goodreads.presentation.answer_quiz.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import uba.fi.goodreads.presentation.answer_quiz.composables.AnswerQuizRoute


const val ANSWER_QUIZ_ROUTE = "answer_quiz/{bookId}/{quizId}"
private const val DEEP_LINK_URI_PATTERN =
    "goodReads://answer_quiz"

fun NavController.navigateToAnswerQuiz(bookId: String, quizId: String? = null, navOptions: NavOptions? = null) = navigate(
    "answer_quiz/$bookId/$quizId", navOptions
)

fun NavGraphBuilder.answerQuizScreen(
    onBack: () -> Unit
) {
    composable(
        route = ANSWER_QUIZ_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
    ) { navBackResult ->
        val bookId = navBackResult.arguments?.getString("bookId") ?: ""
        val quizId = navBackResult.arguments?.getString("quizId") ?: ""
        AnswerQuizRoute(
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
    destination: AnswerQuizDestination,
    onBack: () -> Unit
) {
    when (destination) {
        AnswerQuizDestination.Back -> onBack()
    }
}