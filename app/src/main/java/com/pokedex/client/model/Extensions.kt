package com.pokedex.client.model

class Extensions {
    interface CONSTANTS{
        companion object{
            val BASE_URL = "https://pokeapi.co/api/v2/"
        }
    }

    interface FUNCTIONS{
        companion object{
            fun retrieveNumberFromUrl(url: String): String {
                // Remove the base URL
                val suffix = url.removePrefix("https://pokeapi.co/api/v2/pokemon/")
                // Remove the trailing "/"
                return suffix.removeSuffix("/")
            }
        }
    }
}