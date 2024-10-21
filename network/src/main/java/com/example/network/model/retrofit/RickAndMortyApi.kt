package com.example.network.model.retrofit

import com.example.network.model.CharacterResponse

import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}

