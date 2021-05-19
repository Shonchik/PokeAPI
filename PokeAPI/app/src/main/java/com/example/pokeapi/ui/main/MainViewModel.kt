package com.example.pokeapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokeapi.mapper.PokeMapper
import com.example.pokeapi.network.PokeApiService
import com.example.pokeapi.paging.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    apiService: PokeApiService,
) : ViewModel() {

    val pokeList = Pager(
        PagingConfig(10, enablePlaceholders = false),
        pagingSourceFactory = { RemoteDataSource(apiService) }
    ).flow
        .map { it.map(PokeMapper::apiToUIModel) }
        .cachedIn(viewModelScope)
}