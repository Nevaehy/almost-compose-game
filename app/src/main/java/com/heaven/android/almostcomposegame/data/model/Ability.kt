package com.heaven.android.almostcomposegame.data.model

import com.heaven.almostgame.data.AbilityType
import com.heaven.almostgame.data.DamageType
import com.heaven.almostgame.data.Debuff

data class Ability(
    var name: String,
    var imageId: Int = 0,
    var description: String,
    var cooldown: Int
) {
    var cooldownLeft = 0
    var type = AbilityType.PASSIVE
    var damageType = DamageType.PHYSICAL
    var mainValue = 0f
    var secondaryValue = 0f
    var debuff = Debuff()

    constructor() : this("", 0, "", 0)

    constructor(
        name: String,
        imageId: Int = 0,
        description: String,
        cooldown: Int,
        type: AbilityType,
        damageType: DamageType,
        mainValue: Float
    ) : this(name, imageId, description, cooldown) {
        this.type = type
        this.damageType = damageType
        this.mainValue = mainValue
    }

    constructor(
        name: String,
        imageId: Int = 0,
        description: String,
        cooldown: Int,
        type: AbilityType,
        damageType: DamageType,
        mainValue: Float,
        debuff: Debuff
    ) : this(name, imageId, description, cooldown) {
        this.type = type
        this.damageType = damageType
        this.mainValue = mainValue
        this.debuff = debuff
    }

    fun getCopy() : Ability {
        return Ability(
            name,
            imageId,
            description,
            cooldown
        ).apply {
            this.cooldownLeft = this@Ability.cooldownLeft
            this.type = this@Ability.type
            this.damageType = this@Ability.damageType
            this.mainValue = this@Ability.mainValue
            this.secondaryValue = this@Ability.secondaryValue
            this.debuff = this@Ability.debuff
        }
    }

}