package com.example.simplerick.room_database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteCharacterViewModel (private val repository: FavoriteCharacterRepository) : ViewModel() {

    val favorites: LiveData<List<FavoriteCharacter>> = repository.favorites

     fun addFavorites (character: FavoriteCharacter){
        viewModelScope.launch { repository.addFavorite(character) }

    }

    fun removeFavorite (character: FavoriteCharacter){
        viewModelScope.launch { repository.removeFavorite(character) }
    }
}