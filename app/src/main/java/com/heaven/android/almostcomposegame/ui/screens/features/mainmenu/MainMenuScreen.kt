package com.heaven.android.almostcomposegame.ui.screens.features.mainmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heaven.android.almostcomposegame.R
import com.heaven.android.almostcomposegame.navigation.features.mainmenu.MainMenuRoutes
import com.heaven.android.almostcomposegame.ui.theme.BackgroundGradient

@Composable
fun MainMenuScreen(
    onClick: (MainMenuRoutes) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {
        MainMenuButton(stringResource(R.string.neutrals)) { onClick(MainMenuRoutes.NeutralsScreen()) }
        MainMenuButton(stringResource(R.string.followers)) { onClick(MainMenuRoutes.FollowersScreen()) }
        MainMenuButton(stringResource(R.string.arena)) { onClick(MainMenuRoutes.PrepareArenaScreen()) }
    }
}

@Composable
fun MainMenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .size(width = 210.dp, height = 60.dp)
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Composable
@Preview
fun MainMenuPreview() {
    MainMenuScreen() { }
}