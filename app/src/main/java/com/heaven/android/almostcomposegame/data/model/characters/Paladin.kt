package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.data.model.RoundEvent


class Paladin(
    name: String,
    imageId: Int,
    totalHealth: Int,
    damage: Int,
    range: Range,
    override var ability: Ability,
) : Character(name, imageId, totalHealth, damage, range) {


    // Ability
    // Passive: All damage is increased by 30% against Undead.
    // Active: Holy Earth. Paladin sanctifies the earth beneath, dealing 5 magic damage to all enemies each turn for 3 next turns.
    constructor() : this(
        "Paladin",
        R.drawable.paladin,
        185,
        22,
        Range.MELEE,
        Ability(
            name = "Armored",
            description = "Passive: The knight is equipped with good armor and receives 15% less damage from physical attacks.",
            cooldown = 0,
            type = AbilityType.PASSIVE,
            damageType = DamageType.NONE,
            mainValue = 0.15f
        )
    )

    override fun receiveDamage(damage: Int, damageType: DamageType) : Int {
        var damageReceived = damage

        when (damageType) {
            DamageType.HEAL -> currentHealth += damage
            DamageType.PHYSICAL -> {
                damageReceived = (damage * (1 - ability.mainValue)).toInt()
                currentHealth -= damageReceived
            }
            else -> currentHealth -= damageReceived
        }

        if (currentHealth > totalHealth)
            currentHealth = totalHealth

        if (currentHealth <= 0) {
            currentHealth = 0
            state = CreatureState.DEAD
        }

        lastRoundEvent = RoundEvent.Received(damageReceived, damageType)

        return damageReceived
    }

    override fun calculatePotentialDamage(damage: Int, damageType: DamageType): Int {
        return when (damageType) {
            DamageType.PHYSICAL -> (damage * (1 - ability.mainValue)).toInt()
            else -> damage
        }
    }

    override fun getCopy() : Paladin {
        return Paladin(
                name,
                imageId,
                totalHealth,
                damage,
                range,
                ability.getCopy(),
        ).apply {
            this.currentHealth = this@Paladin.currentHealth
        }
    }
}
