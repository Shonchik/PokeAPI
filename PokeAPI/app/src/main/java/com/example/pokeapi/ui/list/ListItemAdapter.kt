package com.example.pokeapi.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.pokeapi.R
import com.example.pokeapi.databinding.ListItemBinding
import com.example.pokeapi.ui.model.Pokemon

class ListItemAdapter(
    private val itemClickCallback: (Pokemon) -> Unit,
) : PagingDataAdapter<Pokemon, ListItemAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = ViewHolder(
        ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position), itemClickCallback)
    }

    class ViewHolder(
        private val binding: ListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon?, clickCallback: (Pokemon) -> Unit) {
            binding.title.text = item?.name
            binding.imageView.load(item?.sprites?.frontDefault) {
                placeholder(R.drawable.ic_pokeball).error(
                    R.drawable.ic_pokeball
                )
            }
            if (item != null) {
                binding.root.setOnClickListener {
                    clickCallback(item)
                }
            } else {
                binding.root.setOnClickListener(null)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon,
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon,
        ) = oldItem == newItem
    }
}