package com.heaven.android.almostcomposegame.data.model.characters

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.almostgame.data.Debuff
import com.heaven.android.almostcomposegame.data.model.Ability
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.data.model.Range
import com.heaven.android.almostcomposegame.data.model.RoundEvent
import com.heaven.android.almostcomposegame.presentation.interfaces.Fightable
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import com.heaven.android.almostcomposegame.utils.FightUtils.getPossibleTargets
import com.heaven.android.almostcomposegame.utils.FightUtils.isAbleToAttack
import kotlin.random.Random
import kotlin.random.nextInt

abstract class Character(
    var name: String,
    var imageId: Int,
    var totalHealth: Int,
    var damage: Int,
    var range: Range
) : Fightable {
    open var ability = Ability()

    var currentHealth: Int = totalHealth
    var state = CreatureState.ALIVE
    var lastRoundEvent: RoundEvent = RoundEvent.Idle
    var debuffs = arrayListOf<Debuff>()

    abstract fun getCopy() : Character

    override fun performAttack(
        currentLine: Int,
        currentPos: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<AttackData> {
        if (state == CreatureState.DEAD) return listOf()

        if (!isAbleToAttack(debuffs)) {
            finishTurn(false)
            return listOf()
        }

        var isAbilityUsed = false

        val attackData = if (ability.cooldownLeft == 0 && ability.type == AbilityType.ACTIVE)
            useAbility(currentLine, currentPos, lastRowIndex, enemies).also {
                isAbilityUsed = true
            }
        else {
            performBasicAttack(currentLine, currentPos, lastRowIndex, enemies)
        }

        finishTurn(isAbilityUsed)
        return attackData
    }

    protected open fun useAbility(
        currentLine: Int,
        currentPos: Int,
        lastRowIndex: Int,
        enemies: List<List<Character?>>
    ): List<AttackData> = listOf()

    protected open fun performBasicAttack(
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
                damageType = DamageType.PHYSICAL
            )
        )
    }

    open fun applyDebuff(debuff: Debuff) {
        debuffs.add(debuff.getCopy())
    }

    open fun receiveDamage(damage: Int, damageType: DamageType) : Int {
        lastRoundEvent = RoundEvent.Received(damage, damageType)

        when (damageType) {
            DamageType.HEAL -> currentHealth += damage
            else -> currentHealth -= damage
        }

        if (currentHealth > totalHealth)
            currentHealth = totalHealth

        if (currentHealth <= 0) {
            currentHealth = 0
            state = CreatureState.DEAD
        }

        return damage
    }

    open fun calculatePotentialDamage(damage: Int, damageType: DamageType) = damage

    private fun finishTurn(isAbilityUsed: Boolean) {
        if (isAbilityUsed) {
            ability.cooldownLeft = ability.cooldown
        } else {
            if (--ability.cooldownLeft <= 0) ability.cooldownLeft = 0
        }

        val debuffIterator = debuffs.iterator()
        while (debuffIterator.hasNext()) {
            if (--debuffIterator.next().debuffDuration <= 0) debuffIterator.remove()
        }
    }
}
