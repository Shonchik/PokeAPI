package com.example.pokeapi.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Abilities>,
    val sprites: Sprites,
    val types: List<PokemonTypes>
) : Parcelable

