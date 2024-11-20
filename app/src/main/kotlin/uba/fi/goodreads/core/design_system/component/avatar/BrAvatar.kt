package uba.fi.goodreads.core.design_system.component.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme


@Composable
fun BrAvatar(
    url: String?,
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.MEDIUM
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url ?: "")
            .crossfade(true)
            .decoderFactory(
                SvgDecoder.Factory()
            )
            .listener(
                onError = { _, throwable ->
                    println("ACAA: url: $url")
                    throwable.throwable.printStackTrace()
                }
            )
            .build(),
        placeholder = painterResource(R.drawable.ic_avatar_fill),
        modifier = modifier
            .size(
                when (size) {
                    AvatarSize.SMALL -> 32.dp
                    AvatarSize.MEDIUM -> 44.dp
                    AvatarSize.LARGE -> 56.dp
                    AvatarSize.XLARGE -> 80.dp
                }
            )
            .clip(CircleShape),
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}

@Composable
@Preview
private fun TwAvatarPreview() {
    GoodReadsTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AvatarSize.entries.forEach { size ->
                BrAvatar(
                    url = "",
                    size = size
                )
            }
        }
    }
}

