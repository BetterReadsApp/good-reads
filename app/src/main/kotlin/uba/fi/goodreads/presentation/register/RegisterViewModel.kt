package uba.fi.goodreads.presentation.register

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
import uba.fi.goodreads.domain.usecase.RegisterUseCase
import uba.fi.goodreads.presentation.register.navigation.RegisterDestination
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: RegisterUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<RegisterUiState> =
        MutableStateFlow(RegisterUiState())
    val screenState: StateFlow<RegisterUiState> = _screenState.asStateFlow()

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

    fun onFirstNameChange(value: String) {
        _screenState.update { state ->
            state.copy(
                firstName = value,
                isError = false,
                buttonEnabled = value.isNotEmpty() && state.email.isNotEmpty()
            )
        }
    }

    fun onLastNameChange(value: String) {
        _screenState.update { state ->
            state.copy(
                lastName = value,
                isError = false,
                buttonEnabled = value.isNotEmpty() && state.email.isNotEmpty()
            )
        }
    }

    fun onAlreadyHaveAccClick() {
        _screenState.update { it.copy(destination = RegisterDestination.Login) }
    }

    fun onAuthorCheckChange(value: Boolean) {
        _screenState.update { state ->
            state.copy(isAuthor = value)
        }
    }

    fun onContinueClick() {
        viewModelScope.launch {
            register(
                email = screenState.value.email,
                password = screenState.value.password,
                firstName = screenState.value.firstName,
                lastName = screenState.value.lastName,
                isAuthor = screenState.value.isAuthor
            ).also { result ->
                when (result) {
                    is RegisterUseCase.Result.Error,
                    RegisterUseCase.Result.UnexpectedError -> _screenState.update { it.copy(isError = true) }
                    RegisterUseCase.Result.Success -> Unit
                }
            }
        }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

}