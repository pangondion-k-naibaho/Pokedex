package com.pokedex.client.view.activity.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pokedex.client.R
import com.pokedex.client.databinding.ActivityDetailBinding
import com.pokedex.client.model.data_class.PokemonDetailInfo
import com.pokedex.client.model.data_class.PokemonResponse
import com.pokedex.client.view.adapter.ListPokemonAbilityAdapter
import com.pokedex.client.view.adapter.ListPokemonAdapter
import com.pokedex.client.viewmodel.detail.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val TAG = DetailActivity::class.java.simpleName
    private var deliveredPokemon = PokemonResponse()
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object{
        const val DELIVERED_POKEMON = "DELIVERED_POKEMON"
        fun newIntent(context: Context, deliveredPokemon: PokemonResponse): Intent = Intent(context, DetailActivity::class.java)
            .putExtra(DELIVERED_POKEMON, deliveredPokemon)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        deliveredPokemon = intent.extras!!.get(DELIVERED_POKEMON) as PokemonResponse
        Log.d(TAG, "deliveredPokemon: $deliveredPokemon")
        Log.d(TAG, "numberRetrieved: ${retrieveNumberFromUrl(deliveredPokemon.url!!)}")
        val idPokemon = retrieveNumberFromUrl(deliveredPokemon.url!!)
        detailViewModel.getDetailPokemon(idPokemon)

        detailViewModel.pokemonDetailInfo.observe(this, {pokemonDetail->
            setUpView(pokemonDetail)
        })

        detailViewModel.isLoading.observe(this, {
            setUpLoading(it)
        })

        detailViewModel.isFail.observe(this, {
            setUpWarning(it)
        })
    }

    private fun setUpLoading(isLoading:Boolean){
        binding.pbDetailPokemon.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setUpWarning(isFail:Boolean){
        Toast.makeText(this@DetailActivity, "Retrieving Detail Pokemon Failed", Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(pokemonDetailInfo: PokemonDetailInfo){
        val imageUrl = pokemonDetailInfo.sprites.front_default
        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivPokemon)

        binding.tvPokemonName.text = deliveredPokemon.name

        binding.rvPokemonAbilities.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = ListPokemonAbilityAdapter(pokemonDetailInfo.abilities!!)
        }
    }

    private fun retrieveNumberFromUrl(url: String): String {
        // Remove the base URL
        val suffix = url.removePrefix("https://pokeapi.co/api/v2/pokemon/")

        // Remove the trailing "/"
        return suffix.removeSuffix("/")
    }
}