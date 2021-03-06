package com.michael.lib_mvp_core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.michael.lib_core.thread.SchedulerProvider
import com.michael.lib_core.viewmodel.BaseViewModel

abstract class BasePresenter<V : BaseContract.View>(baseDispatcher: SchedulerProvider) :
    BaseViewModel(baseDispatcher), BaseContract.Presenter<V>, LifecycleObserver {

    protected var view: V? = null
    private var viewLifecycle: Lifecycle? = null

    override fun attach(view: V, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle

        viewLifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }
}