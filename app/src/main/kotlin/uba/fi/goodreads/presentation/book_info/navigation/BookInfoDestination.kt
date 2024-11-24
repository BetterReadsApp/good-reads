package uba.fi.goodreads.presentation.book_info.navigation

sealed class BookInfoDestination {
    data class CreateQuiz(val bookId: String, val quizId: String?) : BookInfoDestination()
    data class Review(val bookId: String) : BookInfoDestination()
    data class AddBookToShelf(val bookId: String) : BookInfoDestination()
    data class EditBook(val bookId: String) : BookInfoDestination()
    data class AnswerQuiz(val bookId: String, val quizId: String?) : BookInfoDestination()
}