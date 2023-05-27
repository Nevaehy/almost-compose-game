package com.heaven.android.almostcomposegame.presentation.model

import com.heaven.android.almostcomposegame.data.model.characters.Character

data class CharacterFightCard(
    val character: Character? = null,
    var cardState: CardAnimationState = CardAnimationState.Idle
)