package uba.fi.goodreads.presentation.login.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import uba.fi.goodreads.presentation.login.LoginScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.login.LoginUiState
import uba.fi.goodreads.presentation.login.LoginViewModel
import uba.fi.goodreads.presentation.login.navigation.LoginDestination

@Composable
fun LoginRoute(
    navigate: (LoginDestination) -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(screenState.destination) {
        screenState.destination?.let { destination ->
            navigate(destination)
            viewModel.onClearDestination()
        }
    }

    LoginScreen(
        screenState = screenState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onContinueClick = viewModel::onContinueClick,
        onCreateAccountClick = viewModel::onCreateAccountClick
    )
}

@Composable
fun LoginScreen(
    screenState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onContinueClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 48.dp
            ),
            text = "Login",
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
            },
            isError = screenState.isError
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
            isError = screenState.isError,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.weight(1f))

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            onClick = onCreateAccountClick
        ) {
            Text(text = "Create an account")
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
fun LoginScreenPreview(
    @PreviewParameter(LoginScreenPreviewParameterProvider::class) state: LoginUiState
) {
    GoodReadsTheme {
        LoginScreen (
            screenState = state,
            onPasswordChange = {},
            onEmailChange = {},
            onContinueClick = {},
            onCreateAccountClick = {}
        )
    }
}