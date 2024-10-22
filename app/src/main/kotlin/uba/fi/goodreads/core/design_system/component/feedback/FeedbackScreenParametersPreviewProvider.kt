package uba.fi.goodreads.core.design_system.component.feedback

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class FeedbackScreenParametersPreviewProvider: PreviewParameterProvider<FeedbackType> {

    override val values: Sequence<FeedbackType> = sequenceOf(
        FeedbackType.SUCCESS,
        FeedbackType.WARNING,
        FeedbackType.ERROR,
    )

}