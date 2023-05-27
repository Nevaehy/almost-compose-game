package com.heaven.android.almostcomposegame.data.common

import com.heaven.android.almostcomposegame.data.model.characters.Archer
import com.heaven.android.almostcomposegame.data.model.characters.Hermit
import com.heaven.android.almostcomposegame.data.model.characters.HigherVampire
import com.heaven.android.almostcomposegame.data.model.characters.Knight
import com.heaven.android.almostcomposegame.data.model.characters.Paladin
import com.heaven.android.almostcomposegame.data.model.characters.SkeletonMage
import com.heaven.android.almostcomposegame.data.model.characters.SkeletonWarrior
import com.heaven.android.almostcomposegame.data.model.characters.Swordsman
import com.heaven.android.almostcomposegame.data.model.characters.Vampire
import com.heaven.android.almostcomposegame.data.model.characters.VampireLord

class CharactersFactory {

    fun getNeutrals() = listOf(SkeletonWarrior(), SkeletonMage(), Vampire(), HigherVampire())

    fun getFollowers() = listOf(Knight(), Swordsman(), Archer(), Hermit())
}