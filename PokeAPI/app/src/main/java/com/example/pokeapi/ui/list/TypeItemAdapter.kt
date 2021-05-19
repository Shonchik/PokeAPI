package com.example.pokeapi.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.databinding.TypeListItemBinding
import com.example.pokeapi.ui.model.PokemonType

class TypeItemAdapter(private val goo: (item: PokemonType) -> Unit) :
    ListAdapter<PokemonType, TypeItemAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: TypeListItemBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: PokemonType) {
            view.title.text = item.name
            view.root.setOnClickListener {
                goo(item)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TypeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object DiffCallback : DiffUtil.ItemCallback<PokemonType>() {
        override fun areItemsTheSame(oldItem: PokemonType, newItem: PokemonType): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: PokemonType,
            newItem: PokemonType
        ): Boolean {
            return oldItem == newItem
        }
    }

}