package com.michael.feature_pokemon_detail.view

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airbnb.deeplinkdispatch.DeepLink
import com.michael.data_source.model.*
import com.michael.feature_pokemon_detail.R
import com.michael.feature_pokemon_detail.di.DaggerPokemonDetailComponent
import com.michael.feature_pokemon_detail.di.PokemonDetailModule
import com.michael.feature_pokemon_detail.mvp.PokemonDetailContract
import com.michael.feature_pokemon_detail.mvp.PokemonDetailPresenterImpl
import com.michael.lib_core.AppLink
import com.michael.lib_core.AppLink.PokemonDetail.PARAM_NAME
import com.michael.lib_core.AppLink.PokemonDetail.POKEMON_DETAIL
import com.michael.lib_core.BaseActivity
import com.michael.lib_core.ext.hide
import com.michael.lib_core.ext.load
import com.michael.lib_core.ext.show
import com.michael.lib_core.ext.toast
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import javax.inject.Inject

@DeepLink(POKEMON_DETAIL)
class PokemonDetailActivity : BaseActivity(), PokemonDetailContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var pokemonDetailPresenter: PokemonDetailContract.Presenter

    private val itemAdapter = ItemAdapter<EvolutionItem>()
    private val adapter: FastAdapter<EvolutionItem> by lazy { FastAdapter.with(itemAdapter) }

    private val typeItemAdapter = ItemAdapter<TypeItem>()
    private val typeAdapter: FastAdapter<TypeItem> by lazy { FastAdapter.with(typeItemAdapter) }

    private var pokemonName = ""
    private var abilities = listOf<String>()

    override val contentView: Int
        get() = R.layout.activity_pokemon_detail

    override fun initView() {
        pokemonDetailPresenter = ViewModelProviders
            .of(this, viewModelFactory)
            .get(PokemonDetailPresenterImpl::class.java)

        pokemonDetailPresenter.attach(this, lifecycle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Pokemon Detail")

        rvEvolutionChain.overScrollMode = View.OVER_SCROLL_NEVER
        rvEvolutionChain.adapter = adapter

        adapter.onClickListener = { view, _, item, _ ->
            if (!item.isSelected) {
                view?.context?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        AppLink.PokemonDetail.uri(item.pokemon.name)
                    )
                )
            }
            false
        }
        rvType.overScrollMode = View.OVER_SCROLL_NEVER
        rvType.adapter = typeAdapter

        btnAbilities.setOnClickListener {
            it.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    AppLink.PokemonAbilities.uri(pokemonName, abilities)
                )
            )
        }

        shimmerDetails.startShimmer()
        shimmerEvolution.startShimmer()

        scrollView.hide()

        initDeeplink()
    }

    private fun initDeeplink() {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras!!
            val pokemonName = parameters.getString(PARAM_NAME) ?: ""
            this.pokemonName = pokemonName
            pokemonDetailPresenter.fetchPokemonDetail(pokemonName)
            pokemonDetailPresenter.fetchPokemonSpecies(pokemonName)
        }
    }

    override fun initInjector() {
        DaggerPokemonDetailComponent.builder()
            .pokemonDetailModule(PokemonDetailModule(this))
            .build()
            .inject(this)
    }

    override fun showPokemonDetail(pokemon: Pokemon) {
        abilities = pokemon.abilities.map {
            it.ability.name
        }
        progress_bar.hide()
        scrollView.show()
        tvTitle.text = pokemon.name.capitalize()
        tvHeight.text = getString(R.string.pokemon_detail_height, pokemon.height)
        tvWeight.text = getString(R.string.pokemon_detail_weight, pokemon.weight)
        pokemon.sprites.frontDefault?.let {
            ivSprite.load(it)
        }

        pokemon.types.forEach {
            typeItemAdapter.add(
                TypeItem(it)
            )
        }

        while (tableLayout.childCount > 1) {
            tableLayout.removeViewAt(tableLayout.childCount - 1)
        }
        pokemon.stats.forEach {
            tableLayout.addView(createRow(it))
        }
    }

    override fun showPokemonSpecies(species: PokemonSpecies) {
        shimmerDetails.hideShimmer()
        tvGenera.background = null
        tvGenera.text = species.generaDetail()
        tvFlavor.background = null
        tvFlavor.text = "\"${species.flavorDetail()}\""
    }

    override fun showPokemonEvolution(pokemonList: List<Pokemon>) {
        shimmerEvolution.hideShimmer()
        shimmerEvolution.hide()
        pokemonList.forEachIndexed { index, pokemon ->
            itemAdapter.add(
                EvolutionItem(
                    pokemon,
                    index == 0,
                    index == pokemonList.size - 1,
                    pokemon.name == pokemonName
                )
            )
        }
    }

    private fun createRow(stats: PokemonStat): ViewGroup {
        val paddingValue = resources.getDimensionPixelOffset(
            R.dimen.materialize_baseline_grid_small
        )
        val row = TableRow(this)
        val textTitle = TextView(this)
        textTitle.text = stats.stat.name.replace("-", " ").capitalize()
        textTitle.setPadding(paddingValue)
        textTitle.background = ContextCompat.getDrawable(this, R.drawable.table_border)
        val textValue = TextView(this)
        textValue.text = stats.baseStat.toString()
        textValue.setPadding(paddingValue)
        textValue.background = ContextCompat.getDrawable(this, R.drawable.table_border)

        row.addView(textTitle)
        row.addView(textValue)

        return row
    }

    override fun showToast(message: String?) {
        Log.d("tmp", message)
        toast(message)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}