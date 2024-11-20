package uba.fi.goodreads.domain.model

import uba.fi.goodreads.data.books.response.QuizAnswerDto

class QuizAnswer (
    val questionId: Int,
    val selectedChoice: Int
){
    fun toDto() =
        QuizAnswerDto(
            questionId = questionId,
            selectedChoice = selectedChoice + 1
        )
}
