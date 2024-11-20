package uba.fi.goodreads.presentation.answer_quiz

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.model.QuizQuestion


class AnswerQuizScreenPreviewParameterProvider : PreviewParameterProvider<AnswerQuizUIState> {
    override val values: Sequence<AnswerQuizUIState> = sequenceOf(
        getSuccessState(),
    )

    private fun getSuccessState() = AnswerQuizUIState(
        questions = listOf(
            QuizQuestion(
                questionText = "What is the capital of France?",
                options = listOf("Berlin", "Madrid", "Paris", "Rome"),
                correctOptionIndex = 2,
                questionId = 0
            ),
            QuizQuestion(
                questionText = "What is 2 + 2?",
                options = listOf("3", "4", "5", "6"),
                correctOptionIndex = 1,
                questionId = 1
            ),
            QuizQuestion(
                questionText = "Which planet is known as the Red Planet?",
                options = listOf("Earth", "Mars", "Jupiter", "Saturn"),
                correctOptionIndex = 1,
                questionId = 2
            )
        )
    )
}