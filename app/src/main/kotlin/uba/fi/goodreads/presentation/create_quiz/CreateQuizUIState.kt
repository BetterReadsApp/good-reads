package uba.fi.goodreads.presentation.create_quiz

import uba.fi.goodreads.domain.model.QuizQuestion
import uba.fi.goodreads.presentation.answer_quiz.navigation.AnswerQuizDestination

data class CreateQuizUIState(
    val questions: List<QuizQuestion> = emptyList(),
    val destination: AnswerQuizDestination? = null,
)
