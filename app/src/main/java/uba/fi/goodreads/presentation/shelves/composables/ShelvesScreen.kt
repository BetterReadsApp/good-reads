package uba.fi.goodreads.presentation.shelves.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.component.feedback.FeedbackScreen
import uba.fi.goodreads.core.design_system.component.feedback.FeedbackType
import uba.fi.goodreads.core.design_system.component.loading.Loading
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme
import uba.fi.goodreads.domain.model.Book
import uba.fi.goodreads.presentation.home.HomeScreenPreviewParameterProvider
import uba.fi.goodreads.presentation.home.HomeUiState
import uba.fi.goodreads.presentation.home.HomeViewModel
import uba.fi.goodreads.presentation.home.composables.SuccessContent

@Composable
fun ShelvesRoute(
    viewModel: ShelveViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    ShelveScreen(
        screenState = screenState
    )
}

@Composable
fun ShelveScreen(screenState: ShelveUiState) {
    when (screenState) {
        ShelveUiState.Error -> FeedbackScreen(type = FeedbackType.ERROR)
        ShelveUiState.Loading -> Loading()
        is ShelveUiState.Success -> SuccessContent(screenState)
    }
}

