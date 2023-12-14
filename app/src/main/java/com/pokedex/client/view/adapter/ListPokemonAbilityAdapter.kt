package com.pokedex.client.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokedex.client.R
import com.pokedex.client.databinding.LayoutAbilityRvBinding
import com.pokedex.client.model.data_class.DescPokemonAbility

class ListPokemonAbilityAdapter(
    private var data: List<DescPokemonAbility>
): RecyclerView.Adapter<ListPokemonAbilityAdapter.ItemHolder>() {

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item:DescPokemonAbility) = with(itemView){
            val binding = LayoutAbilityRvBinding.bind(itemView)
            binding.tvPokemonAbility.text = item.ability!!.name
            binding.tvHiddenStatus.visibility = if(item.is_hidden) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_ability_rv, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data.get(position))
    }
}