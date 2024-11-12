package uba.fi.goodreads.presentation.register.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.presentation.register.RegisterScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.register.RegisterUiState
import uba.fi.goodreads.presentation.register.RegisterViewModel
import uba.fi.goodreads.presentation.register.navigation.RegisterDestination

@Composable
fun RegisterRoute(
    navigate: (RegisterDestination) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    RegisterScreen(
        screenState = screenState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onAlreadyHaveAccClick = viewModel::onAlreadyHaveAccClick,
        onAuthorCheckChange = viewModel::onAuthorCheckChange,
        onContinueClick = viewModel::onContinueClick
    )
}

@Composable
fun RegisterScreen(
    screenState: RegisterUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onAlreadyHaveAccClick: () -> Unit,
    onAuthorCheckChange: (Boolean) -> Unit,
    onContinueClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 48.dp
            ),
            text = "Register",
            fontSize = 32.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp,
                ),
            value = screenState.email,
            onValueChange = onEmailChange,
            label = {
                Text(text = "Email")
            }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp,
                ),
            value = screenState.password,
            onValueChange = onPasswordChange,
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp,
                ),
            value = screenState.firstName,
            onValueChange = onFirstNameChange,
            label = {
                Text(text = "First name")
            }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp,
                ),
            value = screenState.lastName,
            onValueChange = onLastNameChange,
            label = {
                Text(text = "Last name")
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = screenState.isAuthor,
                onCheckedChange = onAuthorCheckChange
            )
            Text(text = "Are you an author?")
        }

        Spacer(Modifier.weight(1f))

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            onClick = onAlreadyHaveAccClick
        ) {
            Text(text = "Already have an account? Sign in")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            shape = RoundedCornerShape(8.dp),
            onClick = onContinueClick
        ) {
            Text(text = "Continue")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview(
    @PreviewParameter(RegisterScreenPreviewParameterProvider::class) state: RegisterUiState
) {
    GoodReadsTheme {
        RegisterScreen (
            screenState = state,
            onPasswordChange = {},
            onEmailChange = {},
            onContinueClick = {},
            onFirstNameChange = {},
            onAlreadyHaveAccClick = {},
            onAuthorCheckChange = {},
            onLastNameChange = {}
        )
    }
}