package com.example.pokeapi.ui.additional

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.example.pokeapi.R
import com.example.pokeapi.databinding.FragmentPokemonBinding
import com.example.pokeapi.ui.list.AbilitiesItemAdapter
import com.example.pokeapi.ui.list.GridSpacingItemDecoration
import com.example.pokeapi.ui.list.SpacingItemDecoration
import com.example.pokeapi.ui.list.TypeItemAdapter
import com.example.pokeapi.ui.model.Pokemon
import com.example.pokeapi.ui.model.PokemonType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding
    private val viewModel by viewModels<PokemonViewModel>()
    private lateinit var adapter: TypeItemAdapter
    private lateinit var adapterAbilities: AbilitiesItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: Pokemon? = arguments?.getParcelable<Pokemon>("pokemon")
        if (data != null) {
            binding.nameText.text = data.name
            binding.baseExperienceText.text = data.baseExperience.toString()
            binding.weightText.text = data.weight.toString()
            binding.heightText.text = data.height.toString()
            binding.orderText.text = data.order.toString()
            binding.frontSprite.load(data.sprites.frontDefault) {
                placeholder(R.drawable.ic_pokeball).error(
                    R.drawable.ic_pokeball
                )
            }
            binding.backSprite.load(data.sprites.backDefault) {
                placeholder(R.drawable.ic_pokeball).error(
                    R.drawable.ic_pokeball
                )
            }
        }

        binding.list.addItemDecoration(GridSpacingItemDecoration(dpToPx(8)))
        binding.listAbilities.addItemDecoration(SpacingItemDecoration(dpToPx(8)))

        adapter = TypeItemAdapter(::go)
        adapterAbilities = AbilitiesItemAdapter()
        if (data != null) {
            adapterAbilities.submitList(List(data.abilities.size) { data.abilities[it].ability })
        }

        viewModel.types.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.listAbilities.adapter = adapterAbilities
        if (data != null) {
            when (data.types.size) {
                1 -> binding.list.layoutManager = GridLayoutManager(requireContext(), 1)
                2 -> binding.list.layoutManager = GridLayoutManager(requireContext(), 2)
                else -> binding.list.layoutManager = GridLayoutManager(requireContext(), 3)
            }
        }
        binding.listAbilities.layoutManager = LinearLayoutManager(requireContext())
        if (data != null) {
            viewModel.getStarships(data.types)
        }

    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun go(item: PokemonType) {
        //Toast.makeText(requireContext(),item.damageRelations.toString(),Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putParcelable("pokemonType", item)
        val navController = findNavController()
        navController.navigate(R.id.pokemonTypeFragment, bundle)
    }

}