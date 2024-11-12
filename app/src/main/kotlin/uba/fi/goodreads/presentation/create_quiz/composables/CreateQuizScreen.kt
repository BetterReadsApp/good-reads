package uba.fi.goodreads.presentation.create_quiz.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import uba.fi.goodreads.presentation.create_quiz.CreateQuizScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.create_quiz.CreateQuizUIState
import uba.fi.goodreads.presentation.create_quiz.CreateQuizViewModel
import uba.fi.goodreads.presentation.create_quiz.navigation.CreateQuizDestination

@Composable
fun CreateQuizRoute(
    navigate: (CreateQuizDestination) -> Unit,
    viewModel: CreateQuizViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    CreateQuizScreen(
        screenState = screenState,
        onAddQuestion = viewModel::addQuestion,
        onQuestionTextChange = viewModel::updateQuestionText,
        onOptionTextChange = viewModel::updateOptionText,
        onCorrectOptionSelected = viewModel::setCorrectOption,
        onSaveQuiz = viewModel::onSaveQuiz,
    )
}

@Composable
fun CreateQuizScreen(
    screenState: CreateQuizUIState,
    onAddQuestion: () -> Unit,
    onSaveQuiz: () -> Unit,
    onQuestionTextChange: (Int, String) -> Unit,
    onOptionTextChange: (Int, Int, String) -> Unit,
    onCorrectOptionSelected: (Int, Int) -> Unit
) {
    Column (modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f, false)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            screenState.questions.forEachIndexed { questionIndex, question ->
                Text(
                    text = "Question ${questionIndex + 1}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Question Text Field
                TextField(
                    value = question.questionText,
                    onValueChange = { onQuestionTextChange(questionIndex, it) },
                    label = { Text("Question Text") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Options Fields
                question.options.forEachIndexed { optionIndex, option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = question.correctOptionIndex == optionIndex,
                            onClick = { onCorrectOptionSelected(questionIndex, optionIndex) }
                        )
                        TextField(
                            value = option,
                            onValueChange = { onOptionTextChange(questionIndex, optionIndex, it) },
                            label = { Text("Option ${optionIndex + 1}") },
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

            Button(
                onClick = onAddQuestion,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Add Question")
            }
        }

        Button(
            onClick = onSaveQuiz,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Save Quiz")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreateQuizScreenPreview(
    @PreviewParameter(CreateQuizScreenPreviewParameterProvider::class) state: CreateQuizUIState
) {
    GoodReadsTheme {
        CreateQuizScreen(
            state,
            onAddQuestion = {},
            onQuestionTextChange = { _, _ -> },
            onOptionTextChange = { _, _, _ -> },
            onCorrectOptionSelected = { _, _ -> },
            onSaveQuiz = {}
        )
    }
}