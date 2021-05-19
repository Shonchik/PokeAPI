package com.example.pokeapi.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.databinding.AbilityListItemBinding
import com.example.pokeapi.databinding.TypeListItemBinding
import com.example.pokeapi.ui.model.Ability

class AbilitiesItemAdapter() :
    ListAdapter<Ability, AbilitiesItemAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: AbilityListItemBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Ability) {
            view.title.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AbilityListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object DiffCallback : DiffUtil.ItemCallback<Ability>() {
        override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Ability,
            newItem: Ability
        ): Boolean {
            return oldItem == newItem
        }
    }

}