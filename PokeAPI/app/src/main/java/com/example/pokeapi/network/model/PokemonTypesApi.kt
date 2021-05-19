package com.example.pokeapi.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypesApi(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val type: TypeApi
)

@Serializable
data class TypeApi(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)

@Serializable
data class PokemonTypeApi(
    @SerialName("name") val name: String,
    @SerialName("damage_relations") val damageRelations: TypeRelationsApi,
)

@Serializable
data class TypeRelationsApi(
    @SerialName("no_damage_to") val noDamageTo: List<NamedApiResource>,
    @SerialName("half_damage_to") val halfDamageTo: List<NamedApiResource>,
    @SerialName("double_damage_to") val doubleDamageTo: List<NamedApiResource>,
    @SerialName("no_damage_from") val noDamageFrom: List<NamedApiResource>,
    @SerialName("half_damage_from") val halfDamageFrom: List<NamedApiResource>,
    @SerialName("double_damage_from") val doubleDamageFrom: List<NamedApiResource>
)

@Serializable
data class NamedApiResource(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)