package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.data.model.characters.Character

@Composable
fun AbilitiesSet(character: Character?) {
    Column(
        Modifier
            .wrapContentSize()
            .padding(top = 4.dp)
    ) {
        with(character?.ability?.imageId) {
            BoardCardAbility(
                iconId = if (this == 0) null else this,
                cooldown = character?.ability?.cooldownLeft ?: 0
            )
        }
    }
}