package com.michael.feature_pokemon_detail.view

import android.view.View
import com.michael.data_source.model.PokemonType
import com.michael.feature_pokemon_detail.R
import com.michael.lib_core.ext.load
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_type.view.*

class TypeItem(val pokemonType: PokemonType) : AbstractItem<TypeItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_type
    override val type: Int
        get() = R.id.typeItemId

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<TypeItem>(view) {
        override fun bindView(item: TypeItem, payloads: MutableList<Any>) {
            with(view) {
                ivType.load(
                    resources.getIdentifier(
                        item.pokemonType.type.name,
                        "drawable",
                        view.context.packageName
                    )
                )
            }
        }

        override fun unbindView(item: TypeItem) {
            with(view) {
                ivType.background = null
            }
        }
    }
}