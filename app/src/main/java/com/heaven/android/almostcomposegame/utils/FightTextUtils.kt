package com.heaven.android.almostcomposegame.utils

import androidx.compose.ui.graphics.Color
import com.heaven.almostgame.data.DamageType
import com.heaven.android.almostcomposegame.data.model.RoundEvent
import com.heaven.android.almostcomposegame.ui.theme.ColorHeal
import com.heaven.android.almostcomposegame.ui.theme.ColorMagic
import com.heaven.android.almostcomposegame.ui.theme.ColorPhysical

object FightTextUtils {

    fun getDamageColor(type: DamageType?) = when (type) {
            DamageType.PHYSICAL -> ColorPhysical
            DamageType.MAGIC -> ColorMagic
            DamageType.HEAL -> ColorHeal
            else -> Color.White
        }

    fun getDamageText(event: RoundEvent) : String {
        return if (event is RoundEvent.Received) {
            when (event.damageType) {
                DamageType.PHYSICAL, DamageType.MAGIC -> "-${event.damage}"
                DamageType.HEAL -> "+${event.damage}"
                else -> event.damage.toString()
            }
        } else ""
    }
}