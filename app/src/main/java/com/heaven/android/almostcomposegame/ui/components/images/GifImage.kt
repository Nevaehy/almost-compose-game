package com.heaven.android.almostcomposegame.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.onAnimationEnd
import coil.request.repeatCount
import coil.size.Size
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.presentation.model.CardAnimationState
import com.heaven.android.almostcomposegame.presentation.model.CharacterFightCard

@Composable
fun GifImage(
    card: CharacterFightCard,
    modifier: Modifier = Modifier,
    onAnimationEnd: () -> Unit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components { add(ImageDecoderDecoder.Factory()) }
        .build()

    if (card.cardState is CardAnimationState.Receiving) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(data = (card.cardState as CardAnimationState.Receiving).resource ?: R.raw.sword_slash)
                    .size(Size.ORIGINAL)
                    .repeatCount(0)
                    .onAnimationEnd {
                        card.cardState = CardAnimationState.Idle
                        onAnimationEnd()
                    }
                    .build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = modifier,
        )
    }
}