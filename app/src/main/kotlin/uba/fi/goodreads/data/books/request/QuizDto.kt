package uba.fi.goodreads.data.books.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uba.fi.goodreads.data.books.response.QuizQuestionDto

@Serializable
data class QuizDto(
    @SerialName("id") val id: String? = null,
    @SerialName("book_id") val bookId: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("questions") val questions: List<QuizQuestionDto>? = null,
)