package com.pokedex.client.model.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DescPokemonType(
    var slot: Int?= null,
    var type: PokemonType?= null
):Parcelable