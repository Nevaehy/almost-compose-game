package com.heaven.android.almostcomposegame.presentation.mappers

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.presentation.model.CharacterFightCard

class CharacterToArenaCardMapper {
    fun map(characters: SnapshotStateList<SnapshotStateList<Character?>>) : SnapshotStateList<SnapshotStateList<CharacterFightCard>> {
        return characters.map { row ->
            row.map {
                CharacterFightCard(character = it?.getCopy())
            }.toMutableStateList()
        }.toMutableStateList()
    }
}