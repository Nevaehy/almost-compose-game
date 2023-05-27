package com.heaven.android.almostcomposegame.utils

import com.heaven.almostgame.data.Debuff
import com.heaven.almostgame.data.DebuffType
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import java.util.*
import com.heaven.android.almostcomposegame.data.model.characters.Character

/**
 *  enemies always above
 *
 *   (0) (1) (2)
 *     (3) (4)
 *   -----------
 *     (0) (1)
 *   (2) (3) (4)
 *
 *  followers always below
 */
object FightUtils {

    fun getPossibleTargets(
        character: Character,
        currentLine: Int,
        currentPosition: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<Character?> {
        // there should be a decent logic for a board fighting algorithms
        return getAllExistingAndAlive(enemies)
    }

    fun getAllExistingAndAlive(enemies: List<List<Character?>>): List<Character> {
        return enemies
            .flatMap { it.filterNotNull() }
            .filter { it.state == CreatureState.ALIVE }
    }

    fun isAbleToAttack(debuffs: ArrayList<Debuff>) : Boolean {
        return debuffs.find { debuff -> debuff.debuffType == DebuffType.ROOT || debuff.debuffType == DebuffType.STUN } == null
    }
}