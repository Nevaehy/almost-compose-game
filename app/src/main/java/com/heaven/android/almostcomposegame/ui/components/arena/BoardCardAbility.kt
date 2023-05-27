package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.ui.theme.BlackLowAlpha
import com.heaven.android.almostcomposegame.ui.theme.VerticalSilverGradient

private const val ICON_SIZE = 20

@Composable
fun BoardCardAbility(iconId: Int?, cooldown: Int = 0) {
    Box(
        modifier = Modifier.alpha(iconId?.let { 1f } ?: 0.0f)
    ) {
        Image(
            painter = painterResource(iconId?.let { iconId } ?: R.drawable.ability_placeholder),
            contentDescription = "boardCardAbility",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(ICON_SIZE.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    brush = VerticalSilverGradient
                )
        )
        Box(
            modifier = Modifier
                .size((ICON_SIZE - 1).dp)
                .clip(CircleShape)
                .background(if (cooldown > 0) BlackLowAlpha else Color.Transparent)
        )
        Text(
            text = if (cooldown > 0) cooldown.toString() else "",
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )

    }
}

@Composable
@Preview
fun BoardCardAbilityPreview() {
    Column() {
        BoardCardAbility(R.drawable.frostbolt)
        BoardCardAbility(null)
        BoardCardAbility(R.drawable.nature_wrath, 2)
    }
}