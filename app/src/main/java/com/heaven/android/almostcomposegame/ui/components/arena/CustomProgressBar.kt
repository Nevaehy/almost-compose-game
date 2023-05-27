package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.ui.theme.EmptyBarGradient
import com.heaven.android.almostcomposegame.ui.theme.HealthBarGradient

/** @newProgress should be in 0..100 range **/
/** @width will override width in @modifier **/
@Composable
fun CustomProgressBar(
    progress: Int,
    width: Int,
    modifier: Modifier = Modifier,
    text: String? = null
) {

    Box(
        modifier = modifier
            .width(width.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(EmptyBarGradient)
    ) {
        Box(
            modifier = Modifier
                .width((width / 100.0 * progress).dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(3.dp))
                .background(HealthBarGradient)
        )
        text?.let {
            Text(
                text = text,
                style = MaterialTheme.typography.overline,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview
fun CustomProgressBarPreview() {
    CustomProgressBar(
        progress = 50,
        width = 100,
        modifier = Modifier.height(14.dp),
        text = "150 / 250"
    )
}