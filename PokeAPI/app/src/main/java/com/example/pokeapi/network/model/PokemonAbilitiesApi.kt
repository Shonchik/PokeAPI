package com.example.pokeapi.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilitiesApi(
    @SerialName("is_hidden") val isHidden: Boolean,
    @SerialName("slot") val slot: Int,
    @SerialName("ability") val ability: PokemonAbilityApi,
)

@Serializable
data class PokemonAbilityApi(
    @SerialName("name") val name: String,
)