package com.heaven.android.almostcomposegame.data.common

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.heaven.android.almostcomposegame.data.model.characters.Archer
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.data.model.characters.Hermit
import com.heaven.android.almostcomposegame.data.model.characters.HigherVampire
import com.heaven.android.almostcomposegame.data.model.characters.Knight
import com.heaven.android.almostcomposegame.data.model.characters.SkeletonMage
import com.heaven.android.almostcomposegame.data.model.characters.SkeletonWarrior
import com.heaven.android.almostcomposegame.data.model.characters.Swordsman
import com.heaven.android.almostcomposegame.data.model.characters.Vampire
import com.heaven.android.almostcomposegame.presentation.model.CharacterDeckCard

class ArenaFactory {

    fun getEnemySetup(id: Int): SnapshotStateList<SnapshotStateList<Character?>> {
        return when (id) {
            1 -> mutableStateListOf(
                mutableStateListOf(SkeletonMage(), SkeletonMage(), SkeletonMage()),
                mutableStateListOf(SkeletonWarrior(), SkeletonWarrior())
            )
            2 -> mutableStateListOf(
                mutableStateListOf(Vampire(), SkeletonMage(), Vampire()),
                mutableStateListOf(SkeletonWarrior(), SkeletonWarrior())
            )
            3 -> mutableStateListOf(
                mutableStateListOf(SkeletonMage(), SkeletonMage()),
                mutableStateListOf(Vampire(), Vampire(), Vampire())
            )
            4 -> mutableStateListOf(
                mutableStateListOf(SkeletonMage(), SkeletonMage()),
                mutableStateListOf(HigherVampire(), HigherVampire())
            )
            5 -> mutableStateListOf(
                mutableStateListOf(SkeletonMage(), HigherVampire(), SkeletonMage()),
                mutableStateListOf(Vampire(), SkeletonWarrior(), Vampire())
            )
            else -> mutableStateListOf(mutableStateListOf())
        }
    }

    fun getFollowersFormation(): SnapshotStateList<SnapshotStateList<Character?>> {
        return mutableStateListOf(
            mutableStateListOf(null, null),
            mutableStateListOf(null, null, null)
        )
    }

    fun getDeckCards(): SnapshotStateList<CharacterDeckCard> {
        return mutableStateListOf(Knight(), Knight(), Swordsman(), Swordsman(), Archer(), Archer(), Archer(), Hermit(), Hermit())
            .map { CharacterDeckCard(it) }
            .toMutableStateList()
    }

}