package uba.fi.goodreads.presentation.home

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
import uba.fi.goodreads.presentation.home.navigation.HomeDestination
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeed: GetFeedUseCase,
    private val getForYou: GetForYouUseCase,
): ViewModel() {

    private val _screenState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState())
    val screenState: StateFlow<HomeUiState> = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            val feed = getFeed()
            val forYou = getForYou()
            _screenState.update {
                HomeUiState(
                    feed = feed,
                    forYou = forYou
                )
            }
        }
    }

    fun onClearDestination() {
        _screenState.update { it.copy(destination = null) }
    }

    fun onBookClick(id: Int) {
        _screenState.update { it.copy(destination = HomeDestination.BookInfo(id)) }
    }
}