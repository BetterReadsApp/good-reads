package uba.fi.goodreads.core.design_system.component.feedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme

@Composable
fun FeedbackScreen(
    type: FeedbackType,
    title: String? = null,
    description: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Illustration(type = type)

        Spacer(Modifier.height(16.dp))

        Title(title, type)

        Spacer(Modifier.height(16.dp))

        Description(description, type)

    }
}

@Composable
private fun Illustration(type: FeedbackType) {
    Image(
        modifier = Modifier.size(100.dp),
        painter = painterResource(
            id = when (type) {
                FeedbackType.SUCCESS -> R.drawable.illustration_confirmation
                FeedbackType.WARNING -> R.drawable.illustration_warning
                FeedbackType.ERROR -> R.drawable.illustration_error
            }
        ),
        contentDescription = null,
        colorFilter = ColorFilter.tint(
            when (type) {
                FeedbackType.SUCCESS -> Color.Green
                FeedbackType.WARNING -> Color.Yellow
                FeedbackType.ERROR -> MaterialTheme.colorScheme.error
            }

        )
    )
}

@Composable
private fun Title(title: String?, type: FeedbackType) {
    Text(
        text = title ?: stringResource(
            id = when (type) {
                FeedbackType.SUCCESS -> R.string.feedback_screen_success_generic_title
                FeedbackType.WARNING -> R.string.feedback_screen_warning_generic_title
                FeedbackType.ERROR -> R.string.feedback_screen_error_generic_title
            }
        )
    )
}

@Composable
private fun Description(description: String?, type: FeedbackType) {
    Text(
        text = description ?: stringResource(
            id = when (type) {
                FeedbackType.SUCCESS -> R.string.feedback_screen_success_generic_description
                FeedbackType.WARNING -> R.string.feedback_screen_warning_generic_description
                FeedbackType.ERROR -> R.string.feedback_screen_error_generic_description
            }
        ),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview(
    @PreviewParameter(FeedbackScreenParametersPreviewProvider::class) type: FeedbackType
) {
    GoodReadsTheme {
        FeedbackScreen(type)
    }
}