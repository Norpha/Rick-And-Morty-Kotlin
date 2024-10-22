package com.example.network.repository

import com.example.network.model.TvCharacter
import com.example.network.model.retrofit.RickAndMortyApi
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton



class TvCharacterRepository @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getCharacters(): List<TvCharacter> {
        val response = api.getCharacters()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Failed to fetch characters")
        }
    }
}