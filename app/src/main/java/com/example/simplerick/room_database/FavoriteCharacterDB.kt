package com.example.simplerick.room_database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class FavoriteCharacterDB : RoomDatabase(){
    abstract fun favouriteCharacterDao(): FavoriteCharacterDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteCharacterDB? = null

        fun getDatabase(context: Context):  FavoriteCharacterDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteCharacterDB::class.java,
                    "favorite_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }
}
