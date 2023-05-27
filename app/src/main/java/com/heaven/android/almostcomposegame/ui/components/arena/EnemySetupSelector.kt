package com.heaven.android.almostcomposegame.ui.components.arena

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.ui.theme.DarkSilver
import com.heaven.android.almostcomposegame.ui.theme.Silver

@Composable
fun EnemySetupSelector(
    amount: Int,
    selected: Int = 1,
    onSelect: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..amount) {
            SelectorItem(id = i, isSelected = i == selected) {
                onSelect.invoke(it)
            }
        }
    }
}

@Composable
fun SelectorItem(
    id: Int,
    isSelected: Boolean,
    onSelect: (Int) -> Unit
) {
    Text(
        text = id.toString(),
        style = MaterialTheme.typography.caption,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .size(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = if (isSelected) Silver else DarkSilver)
            .clickable { onSelect(id) }
    )
}

@Composable
@Preview
fun EnemySetupSelectorPreview() {
    EnemySetupSelector(5) { }
}