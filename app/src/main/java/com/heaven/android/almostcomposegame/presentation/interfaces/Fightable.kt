package com.heaven.android.almostcomposegame.presentation.interfaces

import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.presentation.model.AttackData

interface Fightable {
    fun performAttack(
        currentLine: Int,
        currentPos: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<AttackData>
}