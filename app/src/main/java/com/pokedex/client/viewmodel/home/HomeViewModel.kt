package com.pokedex.client.viewmodel.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedex.client.model.data_class.ListPokemonResponse
import com.pokedex.client.model.networking.ApiConfig
import retrofit2.Call
import retrofit2.Response

class HomeViewModel:ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    private var _listPokemonResponse = MutableLiveData<ListPokemonResponse>()
    val listPokemonResponse : LiveData<ListPokemonResponse> = _listPokemonResponse

    private var _listPokemonResponse2 = MutableLiveData<ListPokemonResponse>()
    val listPokemonResponse2 : LiveData<ListPokemonResponse> = _listPokemonResponse2

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail : LiveData<Boolean> = _isFail

    fun getListPokemon(offset: Int){
        _isLoading.value = true
        val client1 = ApiConfig.getApiService().getListPokemon(offset = offset)
        client1.enqueue(object: retrofit2.Callback<ListPokemonResponse>{
            override fun onResponse(
                call: Call<ListPokemonResponse>,
                response: Response<ListPokemonResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listPokemonResponse.value = response.body()
                    Log.d(TAG, "Successs")
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListPokemonResponse>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
    fun getMoreListPokemon(offset: Int){
        _isLoading.value = true
        val client1 = ApiConfig.getApiService().getListPokemon(offset = offset)
        client1.enqueue(object: retrofit2.Callback<ListPokemonResponse>{
            override fun onResponse(
                call: Call<ListPokemonResponse>,
                response: Response<ListPokemonResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listPokemonResponse2.value = response.body()
                    Log.d(TAG, "Successs")
                }else{
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListPokemonResponse>, t: Throwable) {
                _isLoading.value = false
                _isFail.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}