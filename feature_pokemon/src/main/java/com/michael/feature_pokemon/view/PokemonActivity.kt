package com.michael.feature_pokemon.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.michael.api.responses.ResultState
import com.michael.feature_pokemon.R
import com.michael.feature_pokemon.di.DaggerPokemonComponent
import com.michael.feature_pokemon.di.PokemonModule
import com.michael.feature_pokemon.mvi.PokemonMVIActions
import com.michael.feature_pokemon.mvi.PokemonState
import com.michael.lib_core.AppLink
import com.michael.lib_core.ext.hide
import com.michael.lib_core.ext.show
import com.michael.lib_core.ext.toast
import com.michael.mvi_core.BaseMVIActivity
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import com.mikepenz.fastadapter.ui.items.ProgressItem
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonActivity : BaseMVIActivity<PokemonState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PokemonMVIViewModel

    private val items = ItemAdapter<GenericItem>()
    private val adapter: FastAdapter<GenericItem> by lazy { FastAdapter.with(items) }
    private val footerAdapter = GenericItemAdapter()

    override fun render(state: PokemonState) {
        when {
            state.isLoading -> {
                if (state.offset == 0 && !state.isPtrRefresh) {
                    progress_bar.show()
                }
            }
            state.errorMessage.isNullOrBlank().not() -> {
                swipeRefreshLayout.isRefreshing = false
                toast(state.errorMessage)
            }
            else -> {
                swipeRefreshLayout.isRefreshing = false
                progress_bar.hide()
                footerAdapter.clear()
                val newList = arrayListOf<PokemonItem>()
                state.pokemonList.forEach {
                    newList.add(PokemonItem(it).apply {
                        identifier = it.name.hashCode().toLong()
                    })
                }
                items.setNewList(newList)
            }
        }
    }

    override val contentView: Int
        get() = R.layout.activity_container

    override fun initView(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(PokemonMVIViewModel::class.java)

        initSwipeRefreshLayout()
        initRecyclerView()
        initObserver()

        if (savedInstanceState == null) {
            viewModel.dispatch(PokemonMVIActions.LoadMore)
        } else {
            if (savedInstanceState["orientation_change"] == false) {
                viewModel.dispatch(PokemonMVIActions.LoadMore)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("orientation_change", true)
        super.onSaveInstanceState(outState)
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            viewModel.dispatch(PokemonMVIActions.Refresh)
        }
    }

    private fun initRecyclerView() {
        adapter.addAdapter(1, footerAdapter)
        adapter.onClickListener = { view, _, item, _ ->
            if (item is PokemonItem) {
                viewModel.dispatch(PokemonMVIActions.Clicked(item.namedApiResource))
                view?.context?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        AppLink.PokemonDetail.uri(item.namedApiResource.name)
                    )
                )
            }
            false
        }

        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(
            recyclerView.layoutManager as RecyclerView.LayoutManager,
            5,
            footerAdapter
        ) {
            override fun onLoadMore(currentPage: Int) {
                if (swipeRefreshLayout.isRefreshing) return
                launch {
                    footerAdapter.clear()
                    footerAdapter.add(ProgressItem())
                }

                viewModel.dispatch(PokemonMVIActions.LoadMore)
            }
        })
        recyclerView.adapter = adapter
    }

    private fun initObserver() {
        viewModel.observableState.observe(this, Observer {
            render(it)
        })
    }

    override fun initInjector() {
        DaggerPokemonComponent.builder()
            .pokemonModule(PokemonModule(this))
            .build()
            .inject(this)
    }
}