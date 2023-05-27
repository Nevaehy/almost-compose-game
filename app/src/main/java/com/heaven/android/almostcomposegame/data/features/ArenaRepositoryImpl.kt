package com.heaven.android.almostcomposegame.data.features

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.heaven.android.almostcomposegame.data.model.characters.Character

class ArenaRepositoryImpl : ArenaRepository {

    override fun setEnemiesToFight(enemies: SnapshotStateList<SnapshotStateList<Character?>>) {
        enemiesToFight = enemies
    }

    override fun setFollowersToFight(followers: SnapshotStateList<SnapshotStateList<Character?>>) {
        followersToFight = followers
    }

    override fun getEnemiesToFight() = enemiesToFight

    override fun getFollowersToFight() = followersToFight

    companion object {
        private var enemiesToFight = mutableStateListOf<SnapshotStateList<Character?>>()
        private var followersToFight = mutableStateListOf<SnapshotStateList<Character?>>()
    }
}