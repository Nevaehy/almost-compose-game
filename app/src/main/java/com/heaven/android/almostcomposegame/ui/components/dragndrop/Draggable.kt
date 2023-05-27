package com.heaven.android.almostcomposegame.ui.components.dragndrop

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import com.heaven.android.almostcomposegame.presentation.utils.DraggableInfo

@Composable
fun <T> Draggable(
    modifier: Modifier,
    dataToDrop: T,
    content: @Composable () -> Unit
) {
    var startPosition by remember { mutableStateOf(Offset.Zero) }
    var startOffset = Offset.Zero

    Box(
        modifier
            .onGloballyPositioned {
                startPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(dataToDrop) {
                detectVerticalDragGestures(
                    onDragStart = {
                        startOffset = it
                        DraggableInfo.setDraggableTarget(content, dataToDrop)
                        DraggableInfo.isDragging = true
                        DraggableInfo.dragPosition = startPosition + it
                    },
                    onVerticalDrag = { change, _ ->
                        change.consume()
                        DraggableInfo.dragOffset = Offset(
                            change.position.x - startOffset.x,
                            change.position.y - startOffset.y
                        )
                    },
                    onDragCancel = {
                        DraggableInfo.reset()
                    },
                    onDragEnd = {
                        DraggableInfo.notifyDropListeners()
                        DraggableInfo.reset()
                    }
                )
            }
    ) {
        content()
    }
}