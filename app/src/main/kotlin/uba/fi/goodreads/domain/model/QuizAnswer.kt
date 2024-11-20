package uba.fi.goodreads.domain.model

import uba.fi.goodreads.data.books.response.QuizAnswerDto
import uba.fi.goodreads.data.books.response.QuizQuestionDto

class QuizAnswer (
    val question_id: Int,
    val selected_choice: Int
){
    fun toDto() =
        QuizAnswerDto(
            questionId = question_id,
            selectedChoice = selected_choice
        )
}
