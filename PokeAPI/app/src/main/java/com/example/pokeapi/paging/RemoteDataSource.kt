package com.example.pokeapi.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokeapi.network.PokeApiService
import com.example.pokeapi.network.model.PokemonApi

class RemoteDataSource(private val apiService: PokeApiService) : PagingSource<Int, PokemonApi>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonApi>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonApi> {
        val offset = params.key ?: 0
        val result = try {
            apiService.getPokemons(offset, params.loadSize)
        } catch (e: Exception) {
            Log.d("dbbd", e.toString())
            return LoadResult.Error(e)
        }

        val results = List(params.loadSize) {
            apiService.getPokemon(result.results[it].name)
        }

        return LoadResult.Page(
            data = results,
            nextKey = offset + params.loadSize,
            prevKey = null,
        )
    }
}