package uba.fi.goodreads.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetFeedUseCase
import uba.fi.goodreads.domain.usecase.GetForYouUseCase
import uba.fi.goodreads.domain.usecase.LoginUseCase
import uba.fi.goodreads.presentation.login.navigation.LoginDestination
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
): ViewModel() {

    private val _screenState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState())
    val screenState: StateFlow<LoginUiState> = _screenState.asStateFlow()

    fun onEmailChange(value: String) {
        _screenState.update { state ->
            state.copy(
                email = value,
                isError = false,
                buttonEnabled = value.isNotEmpty() && state.password.isNotEmpty()
            )
        }
    }

    fun onPasswordChange(value: String) {
        _screenState.update { state ->
            state.copy(
                password = value,
                isError = false,
                buttonEnabled = value.isNotEmpty() && state.email.isNotEmpty()
            )
        }
    }

    fun onContinueClick() {
        viewModelScope.launch {
            login(
                email = screenState.value.email,
                password = screenState.value.password
            ).also { result ->
                when(result) {
                    is LoginUseCase.Result.Error,
                    LoginUseCase.Result.UnexpectedError -> _screenState.update { it.copy(isError = true) }
                    LoginUseCase.Result.Success -> Unit
                }
            }
        }
    }

    fun onCreateAccountClick() {
        _screenState.update { it.copy(destination = LoginDestination.Register) }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }
}