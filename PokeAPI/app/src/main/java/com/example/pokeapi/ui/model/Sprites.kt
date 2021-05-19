package com.example.pokeapi.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sprites(
    val backFemale: String?,
    val backShinyFemale: String?,
    val backDefault: String?,
    val frontFemale: String?,
    val frontShinyFemale: String?,
    val backShiny: String?,
    val frontDefault: String?,
    val frontShiny: String?
) : Parcelable