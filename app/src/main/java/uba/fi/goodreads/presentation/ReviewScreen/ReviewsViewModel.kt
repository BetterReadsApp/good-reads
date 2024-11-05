package uba.fi.goodreads.presentation.ReviewScreen

//import androidx.lifecycle.ViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//import javax.inject.Inject
//
//@HiltViewModel
//class ReviewScreenViewModel @Inject constructor(
//) : ViewModel() {
//
//    private val _screenState: MutableStateFlow<BookReviewUIState> =
//        MutableStateFlow(BookReviewUIState())
//    val screenState: StateFlow<BookReviewUIState> = _screenState.asStateFlow()
//
//    private val bookId: String = savedStateHandle["bookId"] ?: ""
//    init {
//        viewModelScope.launch {
//            _screenState.upd
//            }
//        }
//    }
//
//}