package com.heaven.android.almostcomposegame.presentation.features.arena

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heaven.android.almostcomposegame.data.common.ArenaFactory
import com.heaven.android.almostcomposegame.data.features.ArenaRepository
import com.heaven.android.almostcomposegame.data.features.ArenaRepositoryImpl
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.presentation.model.CharacterDeckCard
import com.heaven.android.almostcomposegame.presentation.model.DraggableCharacter

class PrepareArenaViewModel(
    private val arenaFactory: ArenaFactory = ArenaFactory(),
    private val arenaRepo: ArenaRepository = ArenaRepositoryImpl()

) : ViewModel() {

    private val enemies = MutableLiveData<SnapshotStateList<SnapshotStateList<Character?>>>()
    fun enemies(): LiveData<SnapshotStateList<SnapshotStateList<Character?>>> = enemies

    private val followers = MutableLiveData<SnapshotStateList<SnapshotStateList<Character?>>>()
    fun followers(): LiveData<SnapshotStateList<SnapshotStateList<Character?>>> = followers

    private val deckCards = MutableLiveData<SnapshotStateList<CharacterDeckCard>>()
    fun deckCards(): LiveData<SnapshotStateList<CharacterDeckCard>> = deckCards

    private val currentEnemySetup = MutableLiveData(1)
    fun currentEnemySetup(): LiveData<Int> = currentEnemySetup

    init {
        updateDecks()
    }

    fun onEnemySetupSelect(id: Int) {
        currentEnemySetup.value = id
        updateDecks()
    }

    fun onFollowerBoardCardClick(ids: Pair<Int, Int>) {
        followers.value!![ids.first][ids.second]?.let {
            removeCardFromBoard(ids)
            returnCardToDeck(it)
        }
    }

    fun onEnemyCardClick(character: Character, ids: Pair<Int, Int>) {

    }

    fun onCardDrop(data: DraggableCharacter, ids: Pair<Int, Int>) {
        if (data.fromIds == null) {
            followers.value!![ids.first][ids.second]?.let { returnCardToDeck(it) }
            setCardFromDeck(data.card, ids)
        } else {
            swapBoardCards(data, ids)
        }
    }

    fun onFightClick(action: () -> Unit) {
        if (areFollowersSet().not()) return

        arenaRepo.setEnemiesToFight(enemies.value!!)
        arenaRepo.setFollowersToFight(followers.value!!)
        action.invoke()
    }

    private fun updateDecks() {
        enemies.value = arenaFactory.getEnemySetup(currentEnemySetup.value!!)
        followers.value = arenaFactory.getFollowersFormation()
        deckCards.value = arenaFactory.getDeckCards()
    }

    private fun setCardFromDeck(character: Character, ids: Pair<Int, Int>) {
        followers.value!![ids.first][ids.second] = character
        updateDeckCardVisibility(character = character, isVisible = false)
    }

    private fun returnCardToDeck(character: Character) {
        updateDeckCardVisibility(character = character, isVisible = true)
    }

    private fun removeCardFromBoard(ids: Pair<Int, Int>) {
        followers.value!![ids.first][ids.second] = null
    }

    private fun swapBoardCards(data: DraggableCharacter, toIds: Pair<Int, Int>) {
        val tempChar = followers.value!![toIds.first][toIds.second]
        followers.value!![toIds.first][toIds.second] = data.card
        followers.value!![data.fromIds!!.first][data.fromIds!!.second] = tempChar
    }

    private fun updateDeckCardVisibility(character: Character, isVisible: Boolean) {
        deckCards.value = deckCards.value?.map {
            if (it.data == character) it.copy(isVisible = isVisible) else it
        }?.toMutableStateList()
    }

    private fun areFollowersSet() : Boolean {
        followers.value!!.forEach {
            it.forEach { char -> if (char != null ) return true }
        }
        return false
    }

    companion object {

        const val ENEMY_SETUPS = 5
    }

}