package uba.fi.goodreads.presentation.edit_profile

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uba.fi.goodreads.domain.mocks.DomainShelfMocks

class EditProfileScreenPreviewParameterProvider: PreviewParameterProvider<EditProfileUiState> {

    override val values: Sequence<EditProfileUiState> = sequenceOf(
        getState(
            firstName = "Pedro",
            lastName = "Duzac",
        ),
        getState(
            firstName = "Emilia",
            lastName = "Duzac",
        ),
        getState(
            firstName = "Marco",
            lastName = "",
        ),
    )

    private fun getState(
        firstName: String = "",
        lastName : String = "",
    ) = EditProfileUiState(
        firstName = firstName,
        lastName = lastName,
    )
}