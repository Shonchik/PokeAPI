package com.example.pokeapi.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Abilities(
    val isHidden: Boolean,
    val slot: Int,
    val ability: Ability,
) : Parcelable

@Parcelize
data class Ability(
    val name: String,
) : Parcelable
