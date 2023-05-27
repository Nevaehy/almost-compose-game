package com.heaven.android.almostcomposegame.ui.screens.features.arena

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heaven.android.almostcomposegame.presentation.features.arena.FightArenaViewModel
import com.heaven.android.almostcomposegame.presentation.model.CharacterFightCard
import com.heaven.android.almostcomposegame.ui.components.arena.FightBoardCard
import com.heaven.android.almostcomposegame.ui.theme.AlmostComposeGameTheme
import com.heaven.android.almostcomposegame.ui.theme.BackgroundGradient

@Composable
fun ArenaFightScreen() {
    val viewModel: FightArenaViewModel = viewModel()
    val enemies = viewModel.enemies().observeAsState()
    val followers = viewModel.followers().observeAsState()

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {
        BoardSetup(cards = enemies.value!!) { viewModel.onAnimationEnd() }
        BoardSetup(cards = followers.value!!) { viewModel.onAnimationEnd() }
    }
}

@Composable
fun BoardSetup(
    cards: List<List<CharacterFightCard>>,
    onAnimationEnd: () -> Unit
) {
    Column {
        cards.forEach {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            ) {
                it.forEach {
                    FightBoardCard(card = it) { onAnimationEnd() }
                }
            }
        }
    }
}

@Composable
@Preview
fun ArenaFightScreenPreview() {
    AlmostComposeGameTheme() {
        ArenaFightScreen()
    }
}