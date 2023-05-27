package com.heaven.android.almostcomposegame.navigation.features.mainmenu

import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.FIGHT_ARENA
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.PREPARE_ARENA
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.FOLLOWERS
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.MAIN_MENU
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.NEUTRALS

sealed class MainMenuRoutes(open val route: String) {
    data class MainMenu(override val route: String = MAIN_MENU) : MainMenuRoutes(route)
    data class FollowersScreen(override val route: String = FOLLOWERS) : MainMenuRoutes(route)
    data class NeutralsScreen(override val route: String = NEUTRALS) : MainMenuRoutes(route)
    data class PrepareArenaScreen(override val route: String = PREPARE_ARENA) : MainMenuRoutes(route)
    data class FightArenaScreen(override val route: String = FIGHT_ARENA) : MainMenuRoutes(route)
}