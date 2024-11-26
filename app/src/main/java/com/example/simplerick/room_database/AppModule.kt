package com.example.simplerick.room_database

import dagger.Provides
import javax.inject.Singleton

object AppModule {

    @Provides
    @Singleton
    fun provideFavoriteCharacterRepository(dao: FavoriteCharacterDao): FavoriteCharacterRepository {

             return FavoriteCharacterRepository(dao)
}
}