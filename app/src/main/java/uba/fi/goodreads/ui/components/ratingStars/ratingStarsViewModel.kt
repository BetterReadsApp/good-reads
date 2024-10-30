package uba.fi.goodreads.ui.components.ratingStars

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RatingViewModel : ViewModel() {
    private val _rating = MutableStateFlow(0)
    val rating: StateFlow<Int> = _rating

    fun updateRating(newRating: Int) {
        _rating.value = newRating
    }
}
