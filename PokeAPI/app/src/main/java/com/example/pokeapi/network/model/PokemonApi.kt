package com.example.pokeapi.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonApi(
    @SerialName("name") val name: String,
    @SerialName("base_experience") val baseExperience: Int,
    @SerialName("height") val height: Int,
    @SerialName("is_default") val isDefault: Boolean,
    @SerialName("order") val order: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("sprites") val sprites: SpritesApi,
    @SerialName("types") val types: List<TypesApi>,
    @SerialName("abilities") val abilities: List<PokemonAbilitiesApi>,
)

@Serializable
data class SpritesApi(
    @SerialName("back_female") val backFemale: String? = null,
    @SerialName("back_shiny_female") val backShinyFemale: String? = null,
    @SerialName("back_default") val backDefault: String? = null,
    @SerialName("front_female") val frontFemale: String? = null,
    @SerialName("front_shiny_female") val frontShinyFemale: String? = null,
    @SerialName("back_shiny") val backShiny: String? = null,
    @SerialName("front_default") val frontDefault: String? = null,
    @SerialName("front_shiny") val frontShiny: String? = null
)