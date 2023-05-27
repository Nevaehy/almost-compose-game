package com.heaven.android.almostcomposegame.presentation.model

import com.heaven.android.almostcomposegame.data.model.characters.Character

data class DraggableCharacter(
    val card: Character,
    var fromIds: Pair<Int, Int>? = null
)