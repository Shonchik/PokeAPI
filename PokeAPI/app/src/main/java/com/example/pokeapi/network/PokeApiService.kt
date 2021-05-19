package com.example.pokeapi.network

import com.example.pokeapi.network.model.PokemonApi
import com.example.pokeapi.network.model.PokemonTypeApi
import com.example.pokeapi.network.model.PokemonsApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonsApi

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonApi

    @GET("type/{name}")
    suspend fun getPokemonType(@Path("name") name: String): PokemonTypeApi
}