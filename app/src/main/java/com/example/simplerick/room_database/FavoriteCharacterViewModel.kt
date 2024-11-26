package com.example.simplerick.room_database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCharacterViewModel @Inject constructor(private val repository: FavoriteCharacterRepository) : ViewModel() {

    val favorites: StateFlow<List<FavoriteCharacter>> = repository.favorites.stateIn(

        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

     fun addFavorites (character: FavoriteCharacter){
        viewModelScope.launch { repository.addFavorite(character) }

    }

    fun removeFavorite (character: FavoriteCharacter){
        viewModelScope.launch { repository.removeFavorite(character) }
    }
}