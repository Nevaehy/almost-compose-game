package com.heaven.android.almostcomposegame.data.model

import com.heaven.almostgame.data.DamageType

sealed class RoundEvent() {
    object Idle : RoundEvent()
    data class Received(val damage: Int, val damageType: DamageType) : RoundEvent()
}