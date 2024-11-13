package uba.fi.goodreads.presentation.create_quiz

import uba.fi.goodreads.domain.model.QuizQuestion
import uba.fi.goodreads.presentation.create_quiz.navigation.CreateQuizDestination

data class CreateQuizUIState(
    val questions: List<QuizQuestion> = emptyList(),
    val destination: CreateQuizDestination? = null,
)
