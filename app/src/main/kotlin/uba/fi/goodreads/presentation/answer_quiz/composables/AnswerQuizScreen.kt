package uba.fi.goodreads.presentation.answer_quiz.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.QuizAnswer
import uba.fi.goodreads.domain.model.QuizQuestion
import uba.fi.goodreads.presentation.answer_quiz.navigation.AnswerQuizDestination
import uba.fi.goodreads.presentation.answer_quiz.AnswerQuizScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.answer_quiz.AnswerQuizUIState
import uba.fi.goodreads.presentation.answer_quiz.AnswerQuizViewModel

@Composable
fun AnswerQuizRoute(
    navigate: (AnswerQuizDestination) -> Unit,
    viewModel: AnswerQuizViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    AnswerQuizScreen(
        screenState = screenState,
        onSendAnswer = viewModel::onSendAnswer,
        onOptionSelected = viewModel::onOptionSelected
    )
}

@Composable
fun AnswerQuizScreen(
    screenState: AnswerQuizUIState,
    onSendAnswer: () -> Unit,
    onOptionSelected: (Int, Int) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f, false)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            screenState.questions.forEachIndexed { questionIndex, question ->
                Question(
                    questionIndex,
                    question,
                    onOptionSelected = onOptionSelected,
                    answer = screenState.answers[questionIndex]
                    )
            }

            Button(
                onClick = onSendAnswer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = screenState.questions.isNotEmpty()
            ) {
                Text(text = "Send Answers")
            }
        }
    }
}

@Composable
fun Question (
    questionIndex: Int,
    question: QuizQuestion,
    onOptionSelected: (Int,Int) -> Unit,
    answer: QuizAnswer) {
    Text(
        text = "Question ${questionIndex + 1}",
        style = MaterialTheme.typography.headlineMedium
    )
    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = question.questionText,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    question.options.forEachIndexed { optionIndex, option ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = answer.selected_choice == optionIndex,
                onClick = { onOptionSelected(questionIndex, optionIndex) }
            )
            Text(
                text = option,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(16.dp))
}




@Composable
@Preview(showBackground = true)
fun AnswerQuizScreenPreview(
    @PreviewParameter(AnswerQuizScreenPreviewParameterProvider::class) state: AnswerQuizUIState
) {
    GoodReadsTheme {
        AnswerQuizScreen(
            state,
            onOptionSelected = { _, _ -> },
            onSendAnswer = {}
        )
    }
}