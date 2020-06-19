package com.michael.feature_pokemon.view

import android.view.View
import com.michael.data_source.model.NamedApiResource
import com.michael.feature_pokemon.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_simple.view.*

class PokemonItem(var namedApiResource: NamedApiResource) :
    AbstractItem<PokemonItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_simple
    override val type: Int
        get() = R.id.pokemonItemId

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<PokemonItem>(view) {
        override fun bindView(item: PokemonItem, payloads: MutableList<Any>) {
            with(view) {
                tvName.text = view.context.getString(R.string.pokemon_asdf_string)
                tvName.text = view.context.getString(R.string.core_test)
//                tvName.text = item.namedApiResource.name.capitalize()
            }
        }

        override fun unbindView(item: PokemonItem) {
            with(view) {
                tvName.text = null
            }
        }
    }
}