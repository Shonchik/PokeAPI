package com.example.pokeapi.ui.main

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.databinding.MainFragmentBinding
import com.example.pokeapi.ui.list.GridSpacingItemDecoration
import com.example.pokeapi.ui.list.ListItemAdapter
import com.example.pokeapi.ui.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var itemAdapter: ListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(GridSpacingItemDecoration(dpToPx(8)))

        itemAdapter = ListItemAdapter(::goToPokemonFragment)

        binding.list.adapter = itemAdapter
        binding.list.layoutManager = GridLayoutManager(requireContext(), 2)

        itemAdapter.addLoadStateListener {
            val isRefreshing = it.refresh is LoadState.Loading
            if (!isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
            binding.progress.isVisible = isRefreshing
            binding.list.isVisible = it.refresh is LoadState.NotLoading
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            itemAdapter.refresh()
        }

        lifecycleScope.launch {
            viewModel.pokeList.collectLatest {
                itemAdapter.submitData(it)
                Log.d("dbbd", it.toString())
            }
        }
    }

    fun goToPokemonFragment(item: Pokemon) {
        val bundle = Bundle()
        bundle.putParcelable("pokemon", item)
        val navController = findNavController()
        navController.navigate(R.id.pokemonFragment, bundle)
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

}