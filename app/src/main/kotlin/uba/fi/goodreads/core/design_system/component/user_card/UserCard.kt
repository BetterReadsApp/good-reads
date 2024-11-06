package uba.fi.goodreads.core.design_system.component.user_card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uba.fi.goodreads.core.design_system.component.avatar.AvatarSize
import uba.fi.goodreads.core.design_system.component.avatar.BrAvatar
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    pictureUrl: String?,
    firstName: String,
    lastName: String,
    onCardClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .width(280.dp)
            .clickable(onClick = onCardClick),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.width(280.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BrAvatar(
                    url = pictureUrl,
                    size = AvatarSize.MEDIUM
                )

            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "$firstName $lastName"
            )


        }
    }
}

@Composable
@Preview
private fun UserCardPreview() {
    GoodReadsTheme {
        UserCard(
            pictureUrl = "",
            firstName = "Name",
            lastName = "Last",
            onCardClick = {},
        )
    }
}