package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils.getPossibleTargets
import kotlin.random.Random
import kotlin.random.nextInt

class SkeletonWarrior(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {

    private val abilityAnimation = R.raw.empowered_attack

    constructor() : this(
        "Skeleton Warrior",
        R.drawable.skeleton_warrior,
        140,
        16,
        Range.MELEE,
        Ability(
            name = "Empowered Attack",
            imageId = R.drawable.skeletal_blow,
            description = "Skeleton Warrior empowers his next basic attack and deals 15 bonus physical damage.",
            cooldown = 1,
            type = AbilityType.ACTIVE,
            damageType = DamageType.PHYSICAL,
            mainValue = 15f
        )
    )

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
                damage = damage + ability.mainValue.toInt(),
                damageType = ability.damageType,
                attackAnimation = abilityAnimation
            )
        )
    }

    override fun getCopy() : SkeletonWarrior {
        return SkeletonWarrior(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@SkeletonWarrior.currentHealth
        }
    }
}
