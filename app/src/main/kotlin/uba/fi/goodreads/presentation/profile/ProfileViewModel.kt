package uba.fi.goodreads.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetProfileUseCase
import uba.fi.goodreads.domain.usecase.LoginUseCase
import uba.fi.goodreads.presentation.profile.navigation.ProfileDestination
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfile: GetProfileUseCase
): ViewModel() {

    private val _screenState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState())
    val screenState: StateFlow<ProfileUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserProfile().also { result ->
                when (result) {
                    is GetProfileUseCase.Result.Error,
                    is GetProfileUseCase.Result.UnexpectedError -> Unit

                    is GetProfileUseCase.Result.Success -> _screenState.update { state ->
                        state.copy(
                            firstName = result.user.name,
                            lastName = result.user.lastName,
                            followingAmount = result.user.following,
                            followersAmount = result.user.followers,
                            shelves = result.user.shelves,
                            ratedBooks = result.user.ratedBooks
                        )
                    }
                }
            }

        }
    }

    fun onBack() {
        _screenState.update { it.copy(destination = ProfileDestination.Back) }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }
}