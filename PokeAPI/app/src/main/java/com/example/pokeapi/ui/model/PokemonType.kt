package com.example.pokeapi.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonTypes(
    val name: String,
) : Parcelable

@Parcelize
data class PokemonType(
    val name: String,
    val damageRelations: TypeRelations,
) : Parcelable

@Parcelize
data class TypeRelations(
    val noDamageTo: List<NamedResource>,
    val halfDamageTo: List<NamedResource>,
    val doubleDamageTo: List<NamedResource>,
    val noDamageFrom: List<NamedResource>,
    val halfDamageFrom: List<NamedResource>,
    val doubleDamageFrom: List<NamedResource>
) : Parcelable

@Parcelize
data class NamedResource(
    val name: String,
    val url: String,
) : Parcelable