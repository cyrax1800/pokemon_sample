package com.michael.lib_core.viewmodel

import androidx.lifecycle.ViewModel
import com.michael.lib_core.thread.SchedulerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val baseDispatcher: SchedulerProvider): ViewModel(),
    CoroutineScope {

    private val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = baseDispatcher.ui() + supervisorJob

    override fun onCleared() {
        if (isActive && !supervisorJob.isCancelled) {
            supervisorJob.children.map {
                it.cancel()
            }
        }
    }

}