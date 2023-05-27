package com.heaven.android.almostcomposegame.ui.components.dragndrop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import com.heaven.android.almostcomposegame.presentation.utils.DraggableInfo

@Composable
inline fun <reified T> Droppable(
    modifier: Modifier = Modifier,
    crossinline onDrop: (T) -> Unit,
    noinline content: @Composable() (BoxScope.() -> Unit)
) {
    var composableRect = Rect(Offset.Zero, Offset.Zero)

    Box(modifier = modifier.onGloballyPositioned {
        it.boundsInWindow().let { rect -> composableRect = rect }
    }) {
        with(DraggableInfo) {
            dropListeners[content] = {
                if (composableRect.contains(dragPosition + dragOffset) && dataToDrop is T) {
                    onDrop(dataToDrop as T)
                }
            }
        }
        content()
    }
}