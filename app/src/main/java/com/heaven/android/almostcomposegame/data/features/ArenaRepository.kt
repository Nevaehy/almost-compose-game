package com.heaven.android.almostcomposegame.data.features

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.heaven.android.almostcomposegame.data.model.characters.Character

interface ArenaRepository {

    fun setEnemiesToFight(enemies: SnapshotStateList<SnapshotStateList<Character?>>)
    fun setFollowersToFight(followers: SnapshotStateList<SnapshotStateList<Character?>>)
    fun getEnemiesToFight() : SnapshotStateList<SnapshotStateList<Character?>>
    fun getFollowersToFight() : SnapshotStateList<SnapshotStateList<Character?>>
}