package com.example.pokeapi.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonsApi(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonApiUrl>,
)

@Serializable
data class PokemonApiUrl(
    val name: String,
    val url: String,
)