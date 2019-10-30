package com.michael.pokemon.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.michael.feature_pokemon_abilities.di.PokemonAbilitiesDeeplinkModule
import com.michael.feature_pokemon_abilities.di.PokemonAbilitiesDeeplinkModuleLoader
import com.michael.feature_pokemon_detail.di.PokemonDetailDeeplinkModule
import com.michael.feature_pokemon_detail.di.PokemonDetailDeeplinkModuleLoader

@DeepLinkHandler(
    PokemonDetailDeeplinkModule::class,
    PokemonAbilitiesDeeplinkModule::class
)
class DeepLinkRouterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            PokemonDetailDeeplinkModuleLoader(),
            PokemonAbilitiesDeeplinkModuleLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}