package uba.fi.goodreads.data.books.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuizAnswerDto(
    @SerialName("question_id") val questionId: Int? = 0,
    @SerialName("selected_choice") val selectedChoice: Int? = 0

)