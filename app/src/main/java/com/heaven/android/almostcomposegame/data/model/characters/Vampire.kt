package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils
import com.heaven.android.almostcomposegame.utils.FightUtils.getPossibleTargets
import kotlin.random.Random
import kotlin.random.nextInt

class Vampire(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {

    constructor() : this(
        "Vampire",
        R.drawable.vampire,
        150,
        18,
        Range.MELEE,
        Ability(
            name = "Lifesteal",
            description = "Passive: Vampire basic attacks restore 50% of dealt damage as health.",
            cooldown = 0,
            type = AbilityType.PASSIVE,
            damageType = DamageType.HEAL,
            mainValue = 0.5f
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
        val damageDealt = targets[random]!!.calculatePotentialDamage(damage, DamageType.PHYSICAL)

        return listOf(
            AttackData(
                target = targets[random]!!,
                damage = damage,
                damageType = DamageType.PHYSICAL
            ),
            AttackData(
                target = this,
                damage = (damageDealt * ability.mainValue).toInt(),
                damageType = DamageType.HEAL,
                attackAnimation = R.raw.hp_restore
            )
        )
    }

    override fun getCopy() : Vampire {
        return Vampire(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@Vampire.currentHealth
        }
    }
}
