package com.michael.lib_core

import android.net.Uri

object AppLink {
    const val SCHEME = "pokemon"
    const val HOST = "pokeapi.co/api/v2"

    object PokemonDetail {
        const val POKEMON_DETAIL = "$SCHEME://$HOST/pokemon/{name}"
        const val PARAM_NAME = "name"

        fun uri(name: String): Uri {
            return Uri.parse("$SCHEME://$HOST/pokemon/$name")
        }
    }

    object PokemonAbilities {
        const val POKEMON_ABILITIES = "$SCHEME://$HOST/pokemon/{name}/abilities/{abilities}"
        const val PARAM_NAME = "name"
        const val PARAM_ABILITIES = "abilities"

        fun uri(name: String, abilities: List<String>): Uri {
            return Uri.parse("$SCHEME://$HOST/pokemon/$name/abilities/${abilities.joinToString(",")}")
        }
    }
}