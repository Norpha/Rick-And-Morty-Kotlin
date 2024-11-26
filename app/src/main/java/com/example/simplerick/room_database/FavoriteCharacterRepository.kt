package com.example.simplerick.room_database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class FavoriteCharacterRepository (private val dao: FavoriteCharacterDao) {
    val favorites: Flow<List<FavoriteCharacter>> = dao.getFavoriteCharacters()

    suspend fun addFavorite (character: FavoriteCharacter){
        dao.addFavorite(character)
    }

    suspend fun removeFavorite(character: FavoriteCharacter) {
        dao.removeFavorite(character)
    }


}