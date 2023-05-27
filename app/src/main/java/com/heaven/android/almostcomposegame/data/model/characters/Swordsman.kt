package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.data.model.RoundEvent
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils
import kotlin.random.Random
import kotlin.random.nextInt

class Swordsman(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {

    constructor() : this(
        "Swordsman",
        R.drawable.swordsman,
        165,
        16,
        Range.MELEE,
        Ability(
            name = "Sword Master",
            description = "Passive: Swordsman has a 33% chance of extra attack to the same opponent.",
            cooldown = 0,
            type = AbilityType.PASSIVE,
            damageType = DamageType.NONE,
            mainValue = 0.33f
        )
    )

    override fun performBasicAttack(
        currentLine: Int,
        currentPos: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<AttackData> {

        val targets =
            FightUtils.getPossibleTargets(this, currentLine, currentPos, lastRowIndex, enemies)
        if (targets.isEmpty()) return listOf()

        val random = Random.nextInt(targets.indices)
        val chances = List(100) { index -> index < ability.mainValue * 100 }
        val isDoubleAttack = chances[Random.nextInt(chances.indices)]

        return listOf(
            AttackData(
                target = targets[random]!!,
                damage = if (isDoubleAttack) damage * 2 else damage,
                damageType = DamageType.PHYSICAL,
                attackAnimation = if (isDoubleAttack) R.raw.double_sword_slash else null
            )
        )
    }

    override fun getCopy() : Swordsman {
        return Swordsman(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@Swordsman.currentHealth
        }
    }
}
