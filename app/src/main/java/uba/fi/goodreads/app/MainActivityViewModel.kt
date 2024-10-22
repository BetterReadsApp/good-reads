package uba.fi.goodreads.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    val isSignedIn: StateFlow<Boolean> = sessionRepository.isUserLogged
        .stateIn(
            scope = viewModelScope,
            initialValue = false,
            started = SharingStarted.WhileSubscribed(5_000),
        )

    init {
        viewModelScope.launch {
            sessionRepository.listenForUnauthorizedResponses()
        }
    }
}