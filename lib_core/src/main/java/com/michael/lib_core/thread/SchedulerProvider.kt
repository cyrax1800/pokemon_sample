package com.michael.lib_core.thread

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {
    fun ui(): CoroutineDispatcher
}