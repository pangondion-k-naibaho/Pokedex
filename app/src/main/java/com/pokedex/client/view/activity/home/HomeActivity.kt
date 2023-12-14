package com.pokedex.client.view.activity.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokedex.client.databinding.ActivityHomeBinding
import com.pokedex.client.model.data_class.ListPokemonResponse
import com.pokedex.client.model.data_class.PokemonResponse
import com.pokedex.client.view.activity.detail.DetailActivity
import com.pokedex.client.view.adapter.ListPokemonAdapter
import com.pokedex.client.viewmodel.home.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val TAG = HomeActivity::class.java.simpleName

    private val homeViewModel by viewModels<HomeViewModel>()
    private var listPokemonResponse = ListPokemonResponse()
    private var offset:Int? = 0

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()

        homeViewModel.getListPokemon(offset!!)

        homeViewModel.listPokemonResponse.observe(this, {listPokemon ->
            setDataPokemon(listPokemon)
        })

        homeViewModel.isLoading.observe(this, {
            setUpLoading(it)
        })

        homeViewModel.isFail.observe(this, {
            setUpWarning(it)
        })
    }

    private fun setUpLoading(isLoading: Boolean){
        binding.pbListPokemon.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setUpWarning(isFail: Boolean){
        Toast.makeText(this@HomeActivity, "Retrieving List Failed", Toast.LENGTH_SHORT).show()
    }

    private fun setDataPokemon(listPokemon: ListPokemonResponse){
        Log.d(TAG, "listPokemon: $listPokemon")
        binding.rvListPokemon.apply {
            val pokemonAdapter = ListPokemonAdapter(listPokemon.results!!, object: ListPokemonAdapter.ItemListener{
                override fun onItemClicked(item: PokemonResponse) {
                    startActivity(
                        DetailActivity.newIntent(this@HomeActivity, item)
                    )
                }
            })
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = pokemonAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        offset = null
    }
}