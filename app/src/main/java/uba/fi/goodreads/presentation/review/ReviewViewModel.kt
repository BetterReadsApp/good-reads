package uba.fi.goodreads.presentation.review

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uba.fi.goodreads.presentation.review.navigation.ReviewDestination
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
) : ViewModel() {

    private val _screenState: MutableStateFlow<BookReviewUIState> =
        MutableStateFlow(BookReviewUIState())
    val screenState: StateFlow<BookReviewUIState> = _screenState.asStateFlow()


    fun onBack() {
        _screenState.update {
            it.copy(destination = ReviewDestination.Back)
        }
    }

    fun onClearDestination() {
        _screenState.update {
            it.copy(destination = null)
        }
    }
}
