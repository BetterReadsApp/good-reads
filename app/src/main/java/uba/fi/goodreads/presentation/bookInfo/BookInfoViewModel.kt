package uba.fi.goodreads.presentation.bookInfo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uba.fi.goodreads.domain.usecase.GetBookInfoUseCase
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    private val getBookInfoUseCase: GetBookInfoUseCase
): ViewModel() {
    private val _screenstate: MutableStateFlow<BookInfoUIState> =
        MutableStateFlow(BookInfoUIState())
    val screenState: StateFlow<BookInfoUIState> = _screenstate.asStateFlow()

    init {
        viewModelScope.launch {
            val book = getBookInfoUseCase()
            _screenstate.update {
                BookInfoUIState(
                    book = book
                )
            }
        }
    }

}
