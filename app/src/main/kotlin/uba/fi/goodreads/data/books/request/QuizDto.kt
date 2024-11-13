package uba.fi.goodreads.data.books.request

import kotlinx.serialization.SerialName
import uba.fi.goodreads.data.books.response.QuizQuestionDto

data class QuizDto(
    @SerialName("questions") val questions: List<QuizQuestionDto>
)