package com.example.pokeapi.ui.additional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pokeapi.databinding.FragmentPokemonTypeBinding
import com.example.pokeapi.ui.model.PokemonType
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections.max


@AndroidEntryPoint
class PokemonTypeFragment : Fragment() {

    private lateinit var binding: FragmentPokemonTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: PokemonType? = arguments?.getParcelable<PokemonType>("pokemonType")
        if (data != null) {
            val countRowFrom = max(
                listOf(
                    data.damageRelations.doubleDamageFrom.size,
                    data.damageRelations.noDamageFrom.size,
                    data.damageRelations.halfDamageFrom.size,
                )
            )
            val countRowTo = max(
                listOf(
                    data.damageRelations.doubleDamageTo.size,
                    data.damageRelations.noDamageTo.size,
                    data.damageRelations.halfDamageTo.size
                )
            )

            for (i in 0..countRowFrom) {
                val row = TableRow(requireContext())
                val lp: TableRow.LayoutParams =
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                row.setLayoutParams(lp)

                val textView1 = TextView(requireContext())
                val textView2 = TextView(requireContext())
                val textView3 = TextView(requireContext())
                textView1.text = try {
                    data.damageRelations.noDamageFrom[i].name
                } catch (e: Exception) {
                    ""
                }
                textView2.text = try {
                    data.damageRelations.halfDamageFrom[i].name
                } catch (e: Exception) {
                    ""
                }
                textView3.text = try {
                    data.damageRelations.doubleDamageFrom[i].name
                } catch (e: Exception) {
                    ""
                }

                row.addView(textView1)
                row.addView(textView2)
                row.addView(textView3)
                binding.displayLinear.addView(row, i + 2)
            }
            for (i in 0..countRowTo) {
                val row = TableRow(requireContext())
                val lp: TableRow.LayoutParams =
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                row.setLayoutParams(lp)

                val textView1 = TextView(requireContext())
                val textView2 = TextView(requireContext())
                val textView3 = TextView(requireContext())
                textView1.text = try {
                    data.damageRelations.noDamageTo[i].name
                } catch (e: Exception) {
                    ""
                }
                textView2.text = try {
                    data.damageRelations.halfDamageTo[i].name
                } catch (e: Exception) {
                    ""
                }
                textView3.text = try {
                    data.damageRelations.doubleDamageTo[i].name
                } catch (e: Exception) {
                    ""
                }

                row.addView(textView1)
                row.addView(textView2)
                row.addView(textView3)
                binding.displayLinear.addView(row, i + 1)
            }
        }
    }

}