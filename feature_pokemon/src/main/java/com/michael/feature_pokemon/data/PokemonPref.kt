package com.michael.feature_pokemon.data

import android.content.SharedPreferences
import com.michael.lib.preference.ext.string
import javax.inject.Inject

class PokemonPref @Inject constructor(sharedPreferences: SharedPreferences) {
    var clickedPokemonName by sharedPreferences.string()
}