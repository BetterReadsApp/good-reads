package uba.fi.goodreads.presentation.create_quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.model.QuizQuestion
import uba.fi.goodreads.domain.usecase.CreateQuizUseCase
import uba.fi.goodreads.domain.usecase.GetBookInfoUseCase
import uba.fi.goodreads.domain.usecase.GetQuizUseCase
import uba.fi.goodreads.domain.usecase.RateBookUseCase
import uba.fi.goodreads.presentation.book_info.navigation.BookInfoDestination
import javax.inject.Inject

@HiltViewModel
class CreateQuizViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getQuizUseCase: GetQuizUseCase,
    private val createQuizUseCase: CreateQuizUseCase,
) : ViewModel() {

    private val _screenState: MutableStateFlow<CreateQuizUIState> =
        MutableStateFlow(CreateQuizUIState())
    val screenState: StateFlow<CreateQuizUIState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: ""

    init {
        viewModelScope.launch {
            getQuizUseCase(bookId = bookId).let { result ->
                when (result) {
                    is GetQuizUseCase.Result.Success -> {
                        _screenState.update { it.copy(questions = result.quiz) }
                    }
                    else -> {}
                }
            }
        }
    }

    fun addQuestion() {
        val newQuestion = QuizQuestion()
        _screenState.update { it.copy(questions = it.questions + newQuestion) }
    }

    fun updateQuestionText(index: Int, text: String) {
        _screenState.update {
            val updatedQuestions = it.questions.toMutableList()
            updatedQuestions[index] = updatedQuestions[index].copy(questionText = text)
            it.copy(questions = updatedQuestions)
        }
    }

    fun updateOptionText(questionIndex: Int, optionIndex: Int, text: String) {
        _screenState.update {
            val updatedQuestions = it.questions.toMutableList()
            val updatedOptions = updatedQuestions[questionIndex].options.toMutableList()
            updatedOptions[optionIndex] = text
            updatedQuestions[questionIndex] =
                updatedQuestions[questionIndex].copy(options = updatedOptions)
            it.copy(questions = updatedQuestions)
        }
    }

    fun setCorrectOption(questionIndex: Int, optionIndex: Int) {
        _screenState.update {
            val updatedQuestions = it.questions.toMutableList()
            updatedQuestions[questionIndex] =
                updatedQuestions[questionIndex].copy(correctOptionIndex = optionIndex)
            it.copy(questions = updatedQuestions)
        }
    }

    fun onSaveQuiz() {
        viewModelScope.launch {
            createQuizUseCase(
                bookId = bookId,
                questions = screenState.value.questions
            )
        }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

}
