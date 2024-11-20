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
import uba.fi.goodreads.domain.model.QuizQuestion
import uba.fi.goodreads.domain.usecase.CreateQuizUseCase
import uba.fi.goodreads.domain.usecase.GetQuizUseCase
import uba.fi.goodreads.presentation.answer_quiz.navigation.AnswerQuizDestination
import javax.inject.Inject

@HiltViewModel
class AnswerQuizViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getQuizUseCase: GetQuizUseCase,
    private val createQuizUseCase: CreateQuizUseCase,
) : ViewModel() {

    private val _screenState: MutableStateFlow<AnswerQuizUIState> =
        MutableStateFlow(AnswerQuizUIState())
    val screenState: StateFlow<AnswerQuizUIState> = _screenState.asStateFlow()

    private val bookId: String = savedStateHandle["bookId"] ?: ""
    private val quizId: String = savedStateHandle["quizId"] ?: ""

    private var edit: Boolean = false

    init {
        viewModelScope.launch {
            getQuizUseCase(quizId = quizId).let { result ->
                when (result) {
                    is GetQuizUseCase.Result.Success -> {
                        edit = true
                        _screenState.update { it.copy(questions = result.quiz) }
                    }
                    else -> {}
                }
            }
        }
    }


    fun onSendQuiz() {
        viewModelScope.launch {
            answerQuizUseCase(
                edit = edit,
                bookId = bookId,
                quizId = quizId,
                questions = screenState.value.questions
            ).also { result ->
                when(result) {
                    is CreateQuizUseCase.Result.Error,
                    is CreateQuizUseCase.Result.UnexpectedError -> Unit
                    is CreateQuizUseCase.Result.Success -> _screenState.update {
                        it.copy(destination = AnswerQuizDestination.Back)
                    }
                }
            }
        }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

}
