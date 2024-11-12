package uba.fi.goodreads.data.books.response

import kotlinx.serialization.SerialName
import uba.fi.goodreads.domain.model.QuizQuestion

data class QuizQuestionDto(
    @SerialName("title") val questionText: String,
    @SerialName("choice_1") val choice1: String,
    @SerialName("choice_2") val choice2: String,
    @SerialName("choice_3") val choice3: String,
    @SerialName("choice_4") val choice4: String,
    @SerialName("correct_choice") val correctChoiceIndex: Int
) {
    fun toDomain() = QuizQuestion(
        questionText = questionText,
        options = listOf(choice1, choice2, choice3, choice4),
        correctOptionIndex = correctChoiceIndex
    )
}
