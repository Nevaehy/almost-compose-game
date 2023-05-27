package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.ui.theme.DarkSilver
import com.heaven.android.almostcomposegame.ui.theme.Silver
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.presentation.model.CharacterDeckCard
import com.heaven.android.almostcomposegame.presentation.model.DraggableCharacter
import com.heaven.android.almostcomposegame.ui.components.dragndrop.Draggable
import com.heaven.android.almostcomposegame.ui.theme.HorizontalBackgroundSilverGradient

private const val CARD_WIDTH = 45
private const val CARD_HEIGHT = 70

@Composable
fun PlayerDeck(cards: List<CharacterDeckCard>) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Divider(
            thickness = 2.dp,
            modifier = Modifier.background(HorizontalBackgroundSilverGradient)
        )
        LazyRow(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            items(cards) { card ->
                if (card.isVisible) DeckCard(character = card.data)
            }
        }
    }
}

@Composable
fun DeckCard(character: Character) {
    Column {
        Draggable(modifier = Modifier, dataToDrop = DraggableCharacter(character)) {
            Image(
                painter = painterResource(character.imageId),
                contentDescription = "deckCard",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(4.dp)
                    .size(width = CARD_WIDTH.dp, height = CARD_HEIGHT.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        brush = Brush.verticalGradient(
                            listOf(
                                DarkSilver,
                                Silver
                            )
                        )
                    )
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.overline,
            color = Color.White,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(bottom = 4.dp)
        )
    }
}