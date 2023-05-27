package com.heaven.android.almostcomposegame.navigation.graphs

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.FIGHT_ARENA
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.PREPARE_ARENA
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.FOLLOWERS
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.MAIN_MENU
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.NEUTRALS
import com.heaven.android.almostcomposegame.ui.screens.features.arena.ArenaFightScreen
import com.heaven.android.almostcomposegame.ui.screens.features.mainmenu.CharactersScreen
import com.heaven.android.almostcomposegame.ui.screens.features.mainmenu.MainMenuScreen
import com.heaven.android.almostcomposegame.ui.screens.features.mainmenu.MapScreen
import com.heaven.android.almostcomposegame.ui.screens.features.mainmenu.PrepareArenaScreen

@Composable
fun MainMenuNavGraph() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.statusBarsPadding(),
        navController = navController,
        startDestination = MAIN_MENU
    ) {
        composable(route = MAIN_MENU) {
            MainMenuScreen { nav -> navController.navigate(nav.route) }
        }
        composable(route = FOLLOWERS) { CharactersScreen(FOLLOWERS) }
        composable(route = NEUTRALS) { CharactersScreen(NEUTRALS) }
        composable(route = PREPARE_ARENA) {
            PrepareArenaScreen { nav -> navController.navigate(nav.route) }
        }
        composable(route = FIGHT_ARENA) { ArenaFightScreen() }
    }
}

object Destinations {
    const val MAIN_MENU = "mainMenu"
    const val FOLLOWERS = "followers"
    const val NEUTRALS = "neutrals"
    const val PREPARE_ARENA = "prepareArena"
    const val FIGHT_ARENA = "fightArena"
}
