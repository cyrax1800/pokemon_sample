package com.michael.feature_pokemon_abilities.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airbnb.deeplinkdispatch.DeepLink
import com.michael.feature_pokemon_abilities.R
import com.michael.feature_pokemon_abilities.di.DaggerPokemonAbilitiesComponent
import com.michael.feature_pokemon_abilities.di.PokemonAbilitiesModule
import com.michael.lib_core.AppLink
import com.michael.lib_core.BaseActivity
import com.michael.lib_core.ext.toast
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_pokemon_abilities.*
import javax.inject.Inject

@DeepLink(AppLink.PokemonAbilities.POKEMON_ABILITIES)
class PokemonAbilitiesActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PokemonAbilitiesViewModel

    private val items = ItemAdapter<PokemonAbilitiesItem>()
    private val adapter: FastAdapter<PokemonAbilitiesItem> by lazy { FastAdapter.with(items) }

    override val contentView: Int
        get() = R.layout.activity_pokemon_abilities

    override fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(PokemonAbilitiesViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Abilities")

        recyclerView.adapter = adapter

        initObserver()

        initDeeplink()
    }

    private fun initDeeplink() {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras!!
            val pokemonAbilitiesString =
                parameters.getString(AppLink.PokemonAbilities.PARAM_ABILITIES) ?: ""
            val pokemonAbilitiesList = pokemonAbilitiesString.split(",")

            if (pokemonAbilitiesList.isNotEmpty() && pokemonAbilitiesString.isNotBlank()) {
                viewModel.getPokemonAbilities(pokemonAbilitiesList)
            } else {
                toast("Empty Abilities")
                finish()
            }
        }
    }

    private fun initObserver() {
        viewModel.pokemonAbilities.observe(this, Observer {
            val pokemonItems = arrayListOf<PokemonAbilitiesItem>()
            for ((name, state) in it) {
                pokemonItems.add(PokemonAbilitiesItem(name, state))
            }
            items.set(pokemonItems)
            adapter.notifyAdapterDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            toast(it)
        })
    }

    override fun initInjector() {
        DaggerPokemonAbilitiesComponent.builder()
            .pokemonAbilitiesModule(PokemonAbilitiesModule())
            .build()
            .inject(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}