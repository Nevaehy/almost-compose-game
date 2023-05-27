package com.heaven.android.almostcomposegame.presentation.utils

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

object DraggableInfo {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    val dropListeners = mutableMapOf<@Composable BoxScope.() -> Unit, () -> Unit>()
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataToDrop by mutableStateOf<Any?>(null)

    fun setDraggableTarget(composable: @Composable () -> Unit, dataToDrop: Any?) {
        reset()
        draggableComposable = composable
        this.dataToDrop = dataToDrop
    }

    fun notifyDropListeners() {
        dropListeners.forEach { it.value.invoke() }
    }

    fun reset() {
        isDragging = false
        dragPosition = Offset.Zero
        dragOffset = Offset.Zero
        draggableComposable = null
        dataToDrop = null
    }
}