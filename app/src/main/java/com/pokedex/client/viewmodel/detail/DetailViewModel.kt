package com.pokedex.client.viewmodel.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedex.client.model.data_class.PokemonDetailInfo
import com.pokedex.client.model.networking.ApiConfig
import com.pokedex.client.view.activity.detail.DetailActivity
import retrofit2.Call
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val TAG = DetailActivity::class.java.simpleName

    private var _pokemonDetailInfo = MutableLiveData<PokemonDetailInfo>()
    val pokemonDetailInfo : LiveData<PokemonDetailInfo> = _pokemonDetailInfo

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail : LiveData<Boolean> = _isFail

    fun getDetailPokemon(id: String){
        _isLoading.value = true
        val client1 = ApiConfig.getApiService().getDetailPokemon(id)
        client1.enqueue(object: retrofit2.Callback<PokemonDetailInfo>{
            override fun onResponse(
                call: Call<PokemonDetailInfo>,
                response: Response<PokemonDetailInfo>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _pokemonDetailInfo.value = response.body()
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PokemonDetailInfo>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}