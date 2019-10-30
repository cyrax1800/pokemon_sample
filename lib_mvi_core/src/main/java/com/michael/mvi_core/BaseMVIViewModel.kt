package com.michael.mvi_core

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michael.lib_core.thread.SchedulerProvider
import com.michael.lib_core.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

abstract class BaseMVIViewModel<A : BaseActions, S : BaseState, E>(
    baseDispatcher: SchedulerProvider,
    private val initialState: S
) : BaseViewModel(baseDispatcher) {
    protected lateinit var action: A
    protected var state = MutableLiveData<S>()
    protected val currentState: S get() = state.value ?: initialState
    protected val tag by lazy { javaClass.simpleName }

    val observableState: LiveData<S> = MediatorLiveData<S>().apply {
        addSource(state) { data ->
            Log.d(tag, "Updated State: $data")
            setValue(data)
        }
    }

    open fun dispatch(actions: A) {
        Log.d(tag, "Receive Actions: $actions")
    }

    abstract fun reducer(effect: E, currentState: S): S
}