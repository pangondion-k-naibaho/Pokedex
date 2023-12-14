package com.pokedex.client.model.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonAbility(
    var name: String?= null,
    var url: String?= null
):Parcelable