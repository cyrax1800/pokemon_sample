package com.michael.feature_pokemon_detail.view

import android.view.View
import com.michael.data_source.model.Pokemon
import com.michael.feature_pokemon_detail.R
import com.michael.lib_core.ext.hide
import com.michael.lib_core.ext.load
import com.michael.lib_core.ext.show
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_evolution.view.*

class EvolutionItem(
    val pokemon: Pokemon,
    val isFirst: Boolean = false,
    val isLast: Boolean = false,
    override var isSelected: Boolean
) : AbstractItem<EvolutionItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_evolution
    override val type: Int
        get() = R.id.evolutionItemId

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<EvolutionItem>(view) {
        override fun bindView(item: EvolutionItem, payloads: MutableList<Any>) {
            with(view) {
                tvName.text = item.pokemon.name.capitalize()
                item.pokemon.sprites.frontDefault?.let { imageUrl ->
                    ivSprite.load(imageUrl)
                }
                if (item.isFirst) {
                    viewLeft.hide()
                } else {
                    viewLeft.show()
                }
                if (item.isLast) {
                    viewRight.hide()
                } else {
                    viewRight.show()
                }
                if (item.isSelected) {
                    llContent.background = resources.getDrawable(R.drawable.selected_evolution_border)
                } else {
                    llContent.background = null
                }
            }
        }

        override fun unbindView(item: EvolutionItem) {
            with(view) {
                tvName.text = null
                ivSprite.setImageDrawable(null)
                llContent.background = null
            }
        }
    }
}