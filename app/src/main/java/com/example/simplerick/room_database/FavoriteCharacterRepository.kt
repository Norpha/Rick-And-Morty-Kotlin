package com.example.simplerick.room_database

import androidx.lifecycle.LiveData

class FavoriteCharacterRepository (private val dao: FavoriteCharacterDao) {
    val favorites: LiveData<List<FavoriteCharacter>> = dao.getFavoriteCharacters()

    suspend fun addFavorite (character: FavoriteCharacter){
        dao.addFavorite(character)
    }

    suspend fun removeFavorite(character: FavoriteCharacter) {
        dao.removeFavorite(character)
    }
}