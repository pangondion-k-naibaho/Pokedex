package com.pokedex.client.model.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DescPokemonAbility(
    var ability: PokemonAbility?= null,
    var is_hidden: Boolean = false,
    var slot: Int?= null
):Parcelable