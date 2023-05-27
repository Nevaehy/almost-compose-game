package com.heaven.android.almostcomposegame.presentation.model

import com.heaven.almostgame.data.DamageType
import com.heaven.almostgame.data.Debuff
import com.heaven.android.almostcomposegame.data.model.characters.Character

data class AttackData(
    val target: Character,
    val damage: Int,
    val damageType: DamageType,
    val debuff: Debuff? = null,
    val attackAnimation: Int? = null
)