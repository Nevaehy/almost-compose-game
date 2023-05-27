package com.heaven.almostgame.data

class Debuff(
        var debuffType: DebuffType,
        var debuffDuration: Int
) {
    constructor() : this(DebuffType.NONE, 0)

    fun getCopy(): Debuff {
        return Debuff(
                debuffType,
                debuffDuration
        )
    }
}