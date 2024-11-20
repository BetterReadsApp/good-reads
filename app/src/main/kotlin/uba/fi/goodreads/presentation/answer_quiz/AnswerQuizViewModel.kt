package uba.fi.goodreads.presentation.answer_quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.model.QuizAnswer
import uba.fi.goodreads.domain.usecase.AnswerQuizUseCase
import uba.fi.goodreads.domain.usecase.GetQuizUseCase
import uba.fi.goodreads.presentation.answer_quiz.navigation.AnswerQuizDestination
import javax.inject.Inject

@HiltViewModel
class AnswerQuizViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getQuizUseCase: GetQuizUseCase,
    private val answerQuizUseCase: AnswerQuizUseCase,
) : ViewModel() {

    private val _screenState: MutableStateFlow<AnswerQuizUIState> =
        MutableStateFlow(AnswerQuizUIState())
    val screenState: StateFlow<AnswerQuizUIState> = _screenState.asStateFlow()

    private val quizId: String = savedStateHandle["quizId"] ?: ""

    init {
        viewModelScope.launch {
            getQuizUseCase(quizId = quizId).let { result ->
                when (result) {
                    is GetQuizUseCase.Result.Success -> {
                        _screenState.update {
                            it.copy(
                                questions = result.quiz,
                                answers = result.quiz.map { question ->
                                    QuizAnswer(question.questionId, 0)
                                }
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }


    fun onSendAnswer() {
        viewModelScope.launch {
            answerQuizUseCase(
                quizId = quizId,
                answers = screenState.value.answers
            ).also { result ->
                when (result) {
                    is AnswerQuizUseCase.Result.Error -> _screenState.update {
                        it.copy(error = result.description)
                    }
                    is AnswerQuizUseCase.Result.UnexpectedError -> _screenState.update {
                    it.copy(error = "Unknown error")
                }

                    is AnswerQuizUseCase.Result.Success -> _screenState.update {
                        it.copy(destination = AnswerQuizDestination.Back)
                    }
                }
            }
        }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

    fun onOptionSelected(questionIndex: Int, optionIndex: Int, questionId: Int) {
        _screenState.update {
            val updatedAnswers = it.answers.toMutableList()
            updatedAnswers[questionIndex] =
                QuizAnswer(questionId = questionId, selectedChoice = optionIndex)
            it.copy(answers = updatedAnswers)
        }
    }
}
