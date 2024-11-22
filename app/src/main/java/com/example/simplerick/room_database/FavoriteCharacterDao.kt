package com.example.simplerick.room_database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteCharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(character: FavoriteCharacter)

    @Delete
    suspend fun removeFavorite(character: FavoriteCharacter)

    @Query("SELECT * FROM favorite_characters")
    fun getFavoriteCharacters(): LiveData<List<FavoriteCharacter>>
}