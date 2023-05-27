package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.almostgame.data.Debuff
import com.heaven.almostgame.data.DebuffType
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils
import com.heaven.android.almostcomposegame.utils.FightUtils.getPossibleTargets
import kotlin.random.Random
import kotlin.random.nextInt

class Hermit(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {

    constructor() : this(
        "Hermit",
        R.drawable.hermit,
        130,
        16,
        Range.RANGED,
        Ability(
            name = "Wrath Roots",
            imageId = R.drawable.nature_wrath,
            description = "Hermit calls to the nature spirits and releases their wrath on his foe rooting him for 1 second and dealing 28 damage.",
            cooldown = 2,
            type = AbilityType.ACTIVE,
            damageType = DamageType.MAGIC,
            mainValue = 28f,
            debuff = Debuff(
                DebuffType.ROOT,
                1
            )
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
                damageType = ability.damageType,
                debuff = ability.debuff
            )
        )
    }

    override fun getCopy() : Hermit {
        return Hermit(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@Hermit.currentHealth
        }
    }
}
