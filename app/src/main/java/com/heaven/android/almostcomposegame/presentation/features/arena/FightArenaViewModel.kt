package com.heaven.android.almostcomposegame.presentation.features.arena

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heaven.android.almostcomposegame.data.features.ArenaRepository
import com.heaven.android.almostcomposegame.data.features.ArenaRepositoryImpl
import com.heaven.android.almostcomposegame.data.model.CreatureState
import com.heaven.android.almostcomposegame.presentation.model.CardAnimationState
import com.heaven.android.almostcomposegame.presentation.model.CharacterFightCard
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.presentation.mappers.CharacterToArenaCardMapper
import com.heaven.android.almostcomposegame.presentation.model.AttackData
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FightArenaViewModel(
    private val arenaRepo: ArenaRepository = ArenaRepositoryImpl(),
    private val characterCardMapper: CharacterToArenaCardMapper = CharacterToArenaCardMapper()
): ViewModel() {

    private val enemies = MutableLiveData<SnapshotStateList<SnapshotStateList<CharacterFightCard>>>()
    fun enemies(): LiveData<SnapshotStateList<SnapshotStateList<CharacterFightCard>>> = enemies

    private val followers = MutableLiveData<SnapshotStateList<SnapshotStateList<CharacterFightCard>>>()
    fun followers(): LiveData<SnapshotStateList<SnapshotStateList<CharacterFightCard>>> = followers

    private var deferredAnimations = mutableListOf<CompletableDeferred<Unit>>()

    init {
        enemies.value = characterCardMapper.map(arenaRepo.getEnemiesToFight())
        followers.value = characterCardMapper.map(arenaRepo.getFollowersToFight())

        startFight()
    }

    // TODO: will be refactored according to the required logic
    fun startFight() {
        viewModelScope.launch {
            delay(2000)

            while (teamsAreAlive()) {
                makeBoardTurn(followers.value!!, true)
                makeBoardTurn(enemies.value!!, false)
            }
        }
    }

    private fun teamsAreAlive(): Boolean {
        return getAliveCharacters(followers.value!!).isNotEmpty()
                && getAliveCharacters(enemies.value!!).isNotEmpty()
    }

    private fun getAliveCharacters(characters: List<List<CharacterFightCard>>): List<CharacterFightCard> {
        return characters.flatten().filter { it.character?.state == CreatureState.ALIVE }
    }

    private suspend fun makeBoardTurn(characters: List<MutableList<CharacterFightCard>>, areFollowers: Boolean) {
        for (i in characters.indices) {
            for (j in characters[i].indices) {
                val card = characters[i][j]

                if (card.character == null || card.character.state == CreatureState.DEAD) continue

                selectChamp(characters, i, j)
                makeTurn(i, j, areFollowers)
                deselectChamp(characters, i, j)
            }
        }
    }

    private suspend fun selectChamp(characters: List<MutableList<CharacterFightCard>>, row: Int, pos: Int) {
        characters[row][pos] = characters[row][pos].copy(cardState = CardAnimationState.Selecting)
        waitForAnimation()
    }

    private suspend fun makeTurn(row: Int, pos: Int, isFollower: Boolean) {
        val attackers = if (isFollower) followers.value!! else inverseBoard(enemies.value!!)
        val defenders = if (isFollower) inverseBoard(enemies.value!!) else followers.value!!
        val updatedRow = if (isFollower) row else inverseRow(row)

        animateTurn(
            attackers[updatedRow][pos].character!!.performAttack(
                currentLine = updatedRow,
                currentPos = pos,
                lastRowIndex = attackers[updatedRow].lastIndex,
                enemies = defenders.map { it.map { card -> card.character } }
            )
        )

        when (isFollower) {
            true -> enemies.value = enemies.value?.toMutableStateList()
            false -> followers.value = followers.value?.toMutableStateList()
        }
    }

    private fun inverseBoard(characters: List<MutableList<CharacterFightCard>>) = listOf(characters[1], characters[0])

    private fun inverseRow(row: Int) = if (row == 0) 1 else 0

    private suspend fun animateTurn(attacksData: List<AttackData>) {
        attacksData.forEach {
            findCharacterAndSetReceiving(followers.value!!, it)
            findCharacterAndSetReceiving(enemies.value!!, it)
        }

        if (attacksData.isNotEmpty()) waitForAnimation(attacksData.size)

        attacksData.forEach { data ->
            data.target.receiveDamage(data.damage, data.damageType)
            data.debuff?.let { data.target.applyDebuff(it) }
        }
    }

    private fun findCharacterAndSetReceiving(
        characters: List<MutableList<CharacterFightCard>>, attackData: AttackData) {
        characters.forEachIndexed characters@ { i, row ->
            row.forEachIndexed { j, card ->
                if (card.character == attackData.target) {
                    characters[i][j] = card.copy(cardState = CardAnimationState.Receiving(attackData.attackAnimation))
                    return@characters
                }
            }
        }
    }

    private suspend fun deselectChamp(characters: List<MutableList<CharacterFightCard>>, row: Int, pos: Int) {
        characters[row][pos] = characters[row][pos].copy(cardState = CardAnimationState.Deselecting)
        waitForAnimation()
    }

    private suspend fun waitForAnimation(amount: Int = 1) {
        deferredAnimations.clear()
        for (i in 0 until amount) {
            val deferred = CompletableDeferred<Unit>()
            deferredAnimations.add(deferred).also { deferred.await() }
        }
    }

    fun onAnimationEnd() {
        deferredAnimations.forEach {
            if (it.isActive) {
                it.complete(Unit)
                return
            }
        }
    }
}