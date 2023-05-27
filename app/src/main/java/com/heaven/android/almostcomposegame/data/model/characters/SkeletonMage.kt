package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.R
import kotlin.random.Random
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils
import com.heaven.android.almostcomposegame.utils.FightUtils.getPossibleTargets
import kotlin.random.nextInt

class SkeletonMage(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {

    constructor() : this(
            "Skeleton Mage",
            R.drawable.skeleton_mage,
            110,
            20,
            Range.RANGED,
            Ability(
                name = "Frostbolt",
                imageId = R.drawable.frostbolt,
                description = "Skeleton Mage uses elemental magic to create a frostbolt. This ability deals 32 magic damage and can hit any target on the field.",
                cooldown = 1,
                type = AbilityType.ACTIVE,
                damageType = DamageType.MAGIC,
                mainValue = 32f
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
                damage = ability.mainValue.toInt(),
                damageType = ability.damageType
            )
        )
    }

    override fun getCopy() : SkeletonMage {
        return SkeletonMage(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@SkeletonMage.currentHealth
        }
    }
}
