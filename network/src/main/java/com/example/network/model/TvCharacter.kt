package com.example.network.model

data class TvCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: Location
)

data class Location(
    val name: String
)

data class CharacterResponse(
    val results: List<TvCharacter>
)