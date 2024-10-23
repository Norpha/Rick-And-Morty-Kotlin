package com.example.simplerick.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.TvCharacter
import com.example.network.repository.TvCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TvCharacterViewModel @Inject constructor(private val repository: TvCharacterRepository) : ViewModel() {

    private val _characters = MutableLiveData<List<TvCharacter>>()
    val characters: LiveData<List<TvCharacter>> = _characters

    private val _favorites = MutableLiveData<MutableList<TvCharacter>>(mutableListOf())
    val favorites: MutableLiveData<MutableList<TvCharacter>> = _favorites

    fun fetchCharacters() {
        viewModelScope.launch {
            _characters.value = repository.getCharacters()
        }
    }

    fun addToFavorites(character: TvCharacter) {
        val currentFavorites = _favorites.value?.toMutableList() ?: mutableListOf()
        currentFavorites.add(character)
        _favorites.value = currentFavorites
    }

    fun removeFromFavorites(character: TvCharacter) {
        val currentFavorites = _favorites.value?.toMutableList() ?: mutableListOf()
        currentFavorites.remove(character)
        _favorites.value = currentFavorites
    }

    fun isFavorite(character: TvCharacter): Boolean {
        return _favorites.value?.contains(character) == true
    }
}