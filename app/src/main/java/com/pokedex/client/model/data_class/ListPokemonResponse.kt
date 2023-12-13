package com.pokedex.client.model.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ListPokemonResponse(
    var count: Int?= null,
    var next: String?= null,
    var previous: String?= null,
    var results: List<PokemonResponse>?= null
): Parcelable