package uba.fi.goodreads.presentation.answer_quiz

import uba.fi.goodreads.domain.model.QuizAnswer
import uba.fi.goodreads.domain.model.QuizQuestion
import uba.fi.goodreads.presentation.answer_quiz.navigation.AnswerQuizDestination

data class AnswerQuizUIState(
    val questions: List<QuizQuestion> = emptyList(),
    val answers: List<QuizAnswer> = emptyList(),
    val error: String? = null,
    val destination: AnswerQuizDestination? = null,
    val sendEnabled: Boolean = false
)
