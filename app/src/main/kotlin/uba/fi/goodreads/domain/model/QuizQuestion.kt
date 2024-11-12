package uba.fi.goodreads.domain.model

import uba.fi.goodreads.data.books.response.QuizQuestionDto

data class QuizQuestion(
    val questionText: String = "",
    val options: List<String> = List(4) { "" },
    val correctOptionIndex: Int = -1
) {
    fun toDto() =
        QuizQuestionDto(
            questionText = questionText,
            choice1 = options[0],
            choice2 = options[1],
            choice3 = options[2],
            choice4 = options[3],
            correctChoiceIndex = correctOptionIndex
        )
}