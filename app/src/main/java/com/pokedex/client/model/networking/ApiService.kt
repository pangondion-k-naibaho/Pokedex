package com.pokedex.client.model.networking

import com.pokedex.client.model.data_class.ListPokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    fun getListPokemon(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ):Call<ListPokemonResponse>
}