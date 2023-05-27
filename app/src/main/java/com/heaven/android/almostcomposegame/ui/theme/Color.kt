package com.heaven.android.almostcomposegame.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val MainBackgroundFirst = Color(0xFF464885)
val MainBackgroundSecond = Color(0xFF331e66)

val BackgroundGradient = Brush.verticalGradient(
    listOf(MainBackgroundFirst, MainBackgroundSecond)
)

val WhiteMediumAlpha = Color(0x88FFFFFF)
val BlackLowAlpha = Color(0xAA000000)
val BlackMediumAlpha = Color(0x88000000)
val BlackHighAlpha = Color(0x66000000)
val DarkSilver = Color(0xFF282929)
val Silver = Color(0xFF6b6b6b)

val VerticalSilverGradient = Brush.verticalGradient(
    listOf(DarkSilver, Silver)
)
val HorizontalBackgroundSilverGradient = Brush.horizontalGradient(
    listOf(MainBackgroundSecond, Silver, MainBackgroundSecond)
)
val EmptyBarGradient = Brush.verticalGradient(
    listOf(
        Color(0x779d9e9d),
        Color(0x995a5d5a),
        Color(0xff747674)
    )
)

val ColorPhysical = Color(0xFFB37D12)
val ColorMagic = Color(0xFF0AB3D1)
val ColorHeal = Color(0xFF10C72E)

val HealthBarGradient = Brush.verticalGradient(
    listOf(
        Color(0xFF33FF33),
        Color(0xFF008000),
    )
)
