package com.pokedex.client.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokedex.client.R
import com.pokedex.client.databinding.LayoutItemRvBinding
import com.pokedex.client.model.data_class.PokemonResponse

class ListPokemonAdapter(
    private var data: List<PokemonResponse>,
    private val listener: ItemListener?= null
): RecyclerView.Adapter<ListPokemonAdapter.ItemHolder>() {
    interface ItemListener{
        fun onItemClicked(item: PokemonResponse)
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: PokemonResponse, listener: ItemListener) = with(itemView){
            val binding = LayoutItemRvBinding.bind(itemView)
            binding.apply {
                tvPokemonName.text = item.name
                root.setOnClickListener{
                    item.let { it -> listener.onItemClicked(it) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_rv, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position), listener!!)
    }
}