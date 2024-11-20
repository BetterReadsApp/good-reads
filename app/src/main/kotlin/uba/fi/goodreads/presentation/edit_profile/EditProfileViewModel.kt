package uba.fi.goodreads.presentation.edit_profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.data.users.repositories.UsersRepository
import uba.fi.goodreads.domain.usecase.GetProfileUseCase
import uba.fi.goodreads.presentation.edit_profile.navigation.EditProfileDestination
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getUserProfile: GetProfileUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
): ViewModel() {

    private val userId: String? = savedStateHandle["userId"]

    private val _screenState: MutableStateFlow<EditProfileUiState> =
        MutableStateFlow(EditProfileUiState())
    val screenState: StateFlow<EditProfileUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserProfile(userId).also { result ->
                when (result) {
                    is GetProfileUseCase.Result.Error,
                    is GetProfileUseCase.Result.UnexpectedError -> Unit

                    is GetProfileUseCase.Result.Success -> _screenState.update { state ->
                        state.copy(
                            firstName = result.user.name,
                            lastName = result.user.lastName,
                            email = result.user.email ?: "",
                            avatarUrl = result.user.avatarUrl,
                            isAuthor = result.user.isAuthor,
                            showAuthorCheck = !result.user.isAuthor
                        )
                    }
                }
                _screenState.update { it.copy(loading = false) }
            }
        }
    }

    fun onFirstNameChange(value: String) {
        _screenState.update {
            it.copy(
                firstName = value
            )
        }
    }

    fun onLastNameChange(value: String) {
        _screenState.update {
            it.copy(
                lastName = value
            )
        }
    }

    fun onEmailChange(value: String) {
        _screenState.update {
            it.copy(
                email = value
            )
        }
    }

    fun onAvatarChange(value: String) {
        _screenState.update {
            it.copy(
                avatarUrl = value
            )
        }
    }

    fun onAuthorCheckChange(value: Boolean) {
        _screenState.update {
            it.copy(
                isAuthor = value
            )
        }
    }

    fun onSave() {
        _screenState.update { it.copy(loading = true) }
        viewModelScope.launch {
            usersRepository.editUser(
                firstName = screenState.value.firstName,
                lastName = screenState.value.lastName,
                email = screenState.value.email,
                avatarUrl = screenState.value.avatarUrl,
                isAuthor = screenState.value.isAuthor
            )
            _screenState.update { it.copy(loading = false) }
            onBack()
        }
    }

    fun onBack() {
        _screenState.update { it.copy(destination = EditProfileDestination.Back) }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }
}