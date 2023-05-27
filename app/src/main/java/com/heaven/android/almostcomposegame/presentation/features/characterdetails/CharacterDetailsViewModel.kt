package com.heaven.android.almostcomposegame.presentation.features.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heaven.android.almostcomposegame.data.common.CharactersFactory
import com.heaven.android.almostcomposegame.data.model.characters.Character
import com.heaven.android.almostcomposegame.navigation.graphs.Destinations.FOLLOWERS

class CharacterDetailsViewModel(
    private val charactersFactory: CharactersFactory = CharactersFactory()
) : ViewModel() {

    private val characters = MutableLiveData<List<Character>>()
    fun characters(): LiveData<List<Character>> = characters

    fun getCharacters(type: String) {
        characters.value = if (type == FOLLOWERS) {
            charactersFactory.getFollowers()
        } else {
            charactersFactory.getNeutrals()
        }
    }
}