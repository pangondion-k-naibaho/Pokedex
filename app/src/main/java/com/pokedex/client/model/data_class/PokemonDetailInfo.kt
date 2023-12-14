package com.pokedex.client.model.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailInfo(
    var abilities: List<DescPokemonAbility>?= null,
    var base_experience: Int?= null,
    var forms: List<PokemonForm>?= null,
    var height: Int?= null,
    var id: Int?= null,
    var is_default: Boolean = false,
    var name: String?= null,
    var order: Int?= null,
    var species: PokemonSpecies?= null,
    var sprites: PokemonSprites,
    var types: List<DescPokemonType>?= null,
    var weight: Int
):Parcelable