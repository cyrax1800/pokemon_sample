package com.michael.lib_core.thread

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestSchedulerProvider : SchedulerProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Unconfined
}