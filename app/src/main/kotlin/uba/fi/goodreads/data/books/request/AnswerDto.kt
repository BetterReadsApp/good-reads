package uba.fi.goodreads.data.books.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.data.books.response.QuizAnswerDto


@Serializable
data class AnswerDto(
    @SerialName("answers") val answers: List<QuizAnswerDto>? = null,
)