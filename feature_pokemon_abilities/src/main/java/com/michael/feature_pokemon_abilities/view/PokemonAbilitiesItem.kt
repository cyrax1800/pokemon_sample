package com.michael.feature_pokemon_abilities.view

import android.view.View
import com.michael.api.responses.ResultState
import com.michael.data_source.model.Ability
import com.michael.data_source.model.NamedApiResource
import com.michael.feature_pokemon_abilities.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_abilities.view.*

class PokemonAbilitiesItem(val name: String, val ability: ResultState<Ability>) :
    AbstractItem<PokemonAbilitiesItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_abilities
    override val type: Int
        get() = R.id.pokemonAbilitiesItemId

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<PokemonAbilitiesItem>(view) {
        override fun bindView(item: PokemonAbilitiesItem, payloads: MutableList<Any>) {
            with(view) {
                tvName.text = item.name.capitalize()
                when (item.ability) {
                    is ResultState.Success -> {
                        sflEffect.hideShimmer()
                        sflShortEffect.hideShimmer()
                        sflFlavor.hideShimmer()
                        val effectEntries = item.ability.data.effectEntries.find {
                            it.language.name == "en"
                        }
                        tvEffect.text = effectEntries?.effect
                        tvShortEffect.text = effectEntries?.shortEffect
                        tvFlavor.text = item.ability.data.flavorTextEntries.find {
                            it.language.name == "en"
                        }?.flavorText
                    }
                    else -> {
                        sflEffect.startShimmer()
                        sflShortEffect.startShimmer()
                        sflFlavor.startShimmer()
                    }
                }
            }
        }

        override fun unbindView(item: PokemonAbilitiesItem) {
            with(view) {
                tvName.text = null
                tvEffect.text = null
                tvShortEffect.text = null
                tvFlavor.text = null
                sflEffect.hideShimmer()
                sflShortEffect.hideShimmer()
                sflFlavor.hideShimmer()
            }
        }
    }
}