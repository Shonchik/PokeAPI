package com.example.pokeapi.ui.additional

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.mapper.PokeMapper
import com.example.pokeapi.network.PokeApiService
import com.example.pokeapi.ui.model.PokemonType
import com.example.pokeapi.ui.model.PokemonTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val apiService: PokeApiService,
) : ViewModel() {

    val types = MutableLiveData<List<PokemonType>>()

    fun getStarships(name: List<PokemonTypes>) {
        viewModelScope.launch {
            types.value =
                List(name.size) { apiService.getPokemonType(name[it].name) }.map(PokeMapper::apiPokemonTypeToUIModel)
        }
    }

}