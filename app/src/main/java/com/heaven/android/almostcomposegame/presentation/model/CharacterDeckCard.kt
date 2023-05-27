package com.heaven.android.almostcomposegame.presentation.model

import com.heaven.android.almostcomposegame.data.model.characters.Character

data class CharacterDeckCard(
    val data: Character,
    var isVisible: Boolean = true
)
