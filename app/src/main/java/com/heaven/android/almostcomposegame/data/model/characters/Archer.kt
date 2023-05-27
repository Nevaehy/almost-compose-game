package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils
import com.heaven.android.almostcomposegame.utils.FightUtils.getPossibleTargets
import kotlin.random.Random
import kotlin.random.nextInt

class Archer(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {

    constructor() : this(
        "Archer",
        R.drawable.archer,
        120,
        18,
        Range.RANGED,
        Ability(
            name = "Precision Shot",
            imageId = R.drawable.piercing_shot,
            description = "Archer focuses on his target and performs a Precision Shot that deals 150% damage.",
            cooldown = 1,
            type = AbilityType.ACTIVE,
            damageType = DamageType.PHYSICAL,
            mainValue = 1.5f
        )
    )

    override fun performBasicAttack(
        currentLine: Int,
        currentPos: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<AttackData> {

        val targets = getPossibleTargets(this, currentLine, currentPos, lastRowIndex, enemies)
        if (targets.isEmpty()) return listOf()

        val random = Random.nextInt(targets.indices)

        return listOf(
            AttackData(
                target = targets[random]!!,
                damage = damage,
                damageType = DamageType.PHYSICAL,
                attackAnimation = R.raw.arrow
            )
        )
    }

    override fun useAbility(
        currentLine: Int,
        currentPos: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<AttackData> {

        val targets = getPossibleTargets(this, currentLine, currentPos, lastRowIndex, enemies)
        if (targets.isEmpty()) return listOf()

        val random = Random.nextInt(targets.indices)

        return listOf(
            AttackData(
                target = targets[random]!!,
                damage = (damage * ability.mainValue).toInt(),
                damageType = ability.damageType,
                attackAnimation = R.raw.arrow
            )
        )
    }

    override fun getCopy() : Archer {
        return Archer(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@Archer.currentHealth
        }
    }
}
