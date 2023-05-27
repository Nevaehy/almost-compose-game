package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.presentation.model.DraggableCharacter
import com.heaven.android.almostcomposegame.ui.components.dragndrop.Draggable
import com.heaven.android.almostcomposegame.ui.components.dragndrop.Droppable
import com.heaven.android.almostcomposegame.ui.theme.VerticalSilverGradient

private const val CARD_WIDTH = 75
private const val CARD_HEIGHT = 100
private const val HEALTH_BAR_HEIGHT = 14

@Composable
fun PrepareBoardCard(
    character: Character? = null,
    side: Side,
    ids: Pair<Int, Int>,
    onDrop: (DraggableCharacter) -> Unit,
    onClick: (Character) -> Unit
) {
    Column {
        Row {
            AbilitiesSet(character = character)
            Droppable<DraggableCharacter>(
                onDrop = { if (side == Side.FOLLOWER && character != it.card) onDrop(it) }
            ) {
                if (side == Side.FOLLOWER && character != null) {
                    DraggableCardImage(character, onClick, ids)
                } else {
                    CardImage(character, onClick)
                }
            }
        }
        HealthBar(character = character)
    }
}

@Composable
fun DraggableCardImage(
    character: Character,
    onClick: (Character) -> Unit,
    ids: Pair<Int, Int>
) {
    Draggable(
        modifier = Modifier,
        dataToDrop = DraggableCharacter(
            card = character,
            fromIds = ids
        )) {
        Image(
            painter = painterResource(character.imageId),
            contentDescription = "boardCard",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 4.dp)
                .size(width = CARD_WIDTH.dp, height = CARD_HEIGHT.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(15.dp),
                    brush = VerticalSilverGradient
                )
                .clickable { onClick(character) }
        )
    }
}

@Composable
fun CardImage(
    character: Character? = null,
    onClick: (Character) -> Unit
) {
    Image(
        painter = painterResource(character?.imageId ?: R.drawable.card_placeholder),
        contentDescription = "boardCard",
        contentScale = ContentScale.FillBounds,
        alpha = if (character != null) 1f else 0.0f,
        modifier = Modifier
            .padding(start = 4.dp)
            .size(width = CARD_WIDTH.dp, height = CARD_HEIGHT.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(15.dp),
                brush = VerticalSilverGradient
            )
            .clickable { character?.let { onClick(it) } }
    )
}


@Composable
fun ColumnScope.HealthBar(character: Character?) {
    CustomProgressBar(
        progress = getHealthPercentage(character),
        width = CARD_WIDTH,
        text = "${character?.currentHealth} / ${character?.totalHealth}",
        modifier = Modifier
            .height(HEALTH_BAR_HEIGHT.dp)
            .padding(top = 4.dp)
            .align(Alignment.End)
            .alpha(if (character == null) 0f else 1f)
    )
}

private fun getHealthPercentage(character: Character?) : Int {
    return if (character == null) {
        100
    } else {
        (character.currentHealth * 100f / character.totalHealth).toInt()
    }
}

enum class Side {
    ENEMY,
    FOLLOWER
}