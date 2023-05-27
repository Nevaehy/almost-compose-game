package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.RoundEvent
import com.heaven.android.almostcomposegame.data.model.characters.Knight
import com.heaven.android.almostcomposegame.presentation.model.CardAnimationState
import com.heaven.android.almostcomposegame.presentation.model.CharacterFightCard
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.ui.components.images.GifImage
import com.heaven.android.almostcomposegame.ui.theme.BlackLowAlpha
import com.heaven.android.almostcomposegame.ui.theme.FontRubik
import com.heaven.android.almostcomposegame.ui.theme.VerticalSilverGradient
import com.heaven.android.almostcomposegame.utils.FightTextUtils.getDamageColor
import com.heaven.android.almostcomposegame.utils.FightTextUtils.getDamageText
import kotlinx.coroutines.delay

private const val CARD_WIDTH = 75f
private const val CARD_HEIGHT = 100f
private const val SCALE_COEFF = 1.15f
private const val SCALE_DURATION = 500
private const val TEXT_DURATION = 700L
private const val TEXT_FADE_DURATION = 500

@Composable
fun FightBoardCard(
    card: CharacterFightCard,
    onAnimationEnd: () -> Unit
) {
    Column {
        Row {
            AbilitiesSet(character = card.character)
            FightCardContent(card) { onAnimationEnd() }
        }
        HealthBar(character = card.character)
    }
}

@Composable
fun FightCardContent(
    card: CharacterFightCard,
    onAnimationEnd: () -> Unit
) {
    val scale = remember { Animatable(1f) }
    val animationSpec : AnimationSpec<Float> = tween(durationMillis = SCALE_DURATION, easing = LinearEasing)

    LaunchedEffect(card.cardState) {
        val animationResult = when (card.cardState) {
            CardAnimationState.Selecting -> scale.animateTo(SCALE_COEFF, animationSpec)
            CardAnimationState.Deselecting -> scale.animateTo(1f, animationSpec)
            else -> { null }
        }

        animationResult?.let {
            onAnimationEnd()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(card.character?.imageId ?: R.drawable.card_placeholder),
            contentDescription = "FightBoardCard",
            contentScale = ContentScale.FillBounds,
            alpha = if (card.character != null) 1f else 0.0f,
            modifier = Modifier
                .padding(start = 4.dp)
                .size(width = CARD_WIDTH.dp, height = CARD_HEIGHT.dp)
                .scale(scale.value)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(15.dp),
                    brush = VerticalSilverGradient
                )
        )
        if (card.character?.currentHealth == 0) {
            Box(
                modifier = Modifier
                    .size(width = CARD_WIDTH.dp, height = CARD_HEIGHT.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(BlackLowAlpha)
            )
        }
        GifImage(
            card = card,
            modifier = Modifier.size(width = CARD_WIDTH.dp, height = CARD_HEIGHT.dp)
        ) { onAnimationEnd() }
        HealthChangeText(character = card.character)
    }
}

@Composable
fun HealthChangeText(character: Character?) {
    val roundEvent = character?.lastRoundEvent ?: RoundEvent.Idle
    val alpha = remember { Animatable(1f) }
    val animationSpec : AnimationSpec<Float> = tween(durationMillis = TEXT_FADE_DURATION, easing = LinearEasing)

    LaunchedEffect(roundEvent) {
        when (roundEvent) {
            is RoundEvent.Received -> {
                delay(TEXT_DURATION)
                alpha.animateTo(0f, animationSpec)
            }
            else -> { }
        }
        character?.lastRoundEvent = RoundEvent.Idle
        alpha.animateTo(1f)
    }

    Text(
        text = getDamageText(roundEvent),
        textAlign = TextAlign.Center,
        color = getDamageColor((roundEvent as? RoundEvent.Received)?.damageType),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontRubik,
            shadow = Shadow(color = Color.Black, offset = Offset(1f, 1f), blurRadius = 2f)
        ),
        modifier = Modifier.alpha(alpha.value)
    )
}

@Composable
@Preview
fun FightCardContentPreview() {
    FightCardContent(card = CharacterFightCard(Knight())) { }
}