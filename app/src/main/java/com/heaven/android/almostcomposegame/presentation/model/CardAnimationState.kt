package com.heaven.android.almostcomposegame.presentation.model

sealed class CardAnimationState {
    object Idle : CardAnimationState()
    object Selecting : CardAnimationState()
    object Deselecting : CardAnimationState()
    class Receiving(val resource: Int? = null) : CardAnimationState()
}