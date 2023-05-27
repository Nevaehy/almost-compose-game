package com.heaven.android.almostcomposegame.ui.screens.features.mainmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.navigation.features.mainmenu.MainMenuRoutes
import com.heaven.android.almostcomposegame.presentation.features.arena.PrepareArenaViewModel
import com.heaven.android.almostcomposegame.presentation.features.arena.PrepareArenaViewModel.Companion.ENEMY_SETUPS
import com.heaven.android.almostcomposegame.presentation.model.DraggableCharacter
import com.heaven.android.almostcomposegame.ui.components.arena.EnemySetupSelector
import com.heaven.android.almostcomposegame.ui.components.arena.PlayerDeck
import com.heaven.android.almostcomposegame.ui.components.arena.PrepareBoardCard
import com.heaven.android.almostcomposegame.ui.components.arena.Side
import com.heaven.android.almostcomposegame.ui.components.dragndrop.DraggableContainer
import com.heaven.android.almostcomposegame.ui.theme.AlmostComposeGameTheme
import com.heaven.android.almostcomposegame.ui.theme.BackgroundGradient

@Composable
fun PrepareArenaScreen(
    onClick: (MainMenuRoutes) -> Unit
) {
    val viewModel: PrepareArenaViewModel = viewModel()
    val enemies = viewModel.enemies().observeAsState()
    val deckCards = viewModel.deckCards().observeAsState()
    val followers = viewModel.followers().observeAsState()
    val selectedSetup = viewModel.currentEnemySetup().observeAsState()

    DraggableContainer(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGradient)
        ) {
            EnemySetupSelector(
                amount = ENEMY_SETUPS,
                selected = selectedSetup.value!!
            ) { viewModel.onEnemySetupSelect(it) }
            BoardSetup(
                characters = enemies.value!!,
                side = Side.ENEMY,
                onDrop = { ch, ids -> viewModel.onCardDrop(ch, ids) },
                onClick = { ch, ids -> viewModel.onEnemyCardClick(ch, ids) }
            )
            Image(
                painter = painterResource(R.drawable.fight),
                contentDescription = "fightBtn",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .clickable { viewModel.onFightClick { onClick(MainMenuRoutes.FightArenaScreen()) } }
            )
            BoardSetup(
                characters = followers.value!!,
                side = Side.FOLLOWER,
                onDrop = { ch, ids -> viewModel.onCardDrop(ch, ids) },
                onClick = { _, ids -> viewModel.onFollowerBoardCardClick(ids) }
            )
            PlayerDeck(cards = deckCards.value!!)
        }
    }
}

@Composable
fun BoardSetup(
    characters: List<List<Character?>>,
    side: Side,
    onDrop: (DraggableCharacter, Pair<Int, Int>) -> Unit,
    onClick: (Character, Pair<Int, Int>) -> Unit
) {
    characters.forEachIndexed { i, it ->
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            it.forEachIndexed { j, it ->
                PrepareBoardCard(
                    character = it,
                    side = side,
                    ids = Pair(i, j),
                    onDrop = { char -> onDrop(char, Pair(i, j)) },
                    onClick = { char -> onClick(char, Pair(i, j)) }
                )
            }
        }
    }
}

@Composable
@Preview
fun ArenaScreenPreview() {
    AlmostComposeGameTheme {
        PrepareArenaScreen { }
    }
}