package com.heaven.android.almostcomposegame.ui.screens.features.mainmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heaven.android.almostcomposegame.ui.components.arena.CharacterDetails
import com.heaven.android.almostcomposegame.presentation.features.characterdetails.CharacterDetailsViewModel
import com.heaven.android.almostcomposegame.ui.theme.BackgroundGradient

@Composable
fun CharactersScreen(type: String) {
    val viewModel: CharacterDetailsViewModel = viewModel()
    val characters = viewModel.characters().observeAsState()

    LaunchedEffect("init") {
        viewModel.getCharacters(type)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {
        items(characters.value.orEmpty()) { char ->
            CharacterDetails(character = char)
        }
    }
}