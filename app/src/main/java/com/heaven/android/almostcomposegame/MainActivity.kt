package com.heaven.android.almostcomposegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.heaven.android.almostcomposegame.navigation.graphs.MainMenuNavGraph
import com.heaven.android.almostcomposegame.ui.theme.AlmostComposeGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlmostComposeGameTheme {
                // A surface container using the 'background' color from the theme
                MainMenuNavGraph()
            }
        }
    }
}