package com.michael.lib.preference

import android.content.Context

object PrefConfig : PrefConfigContract {
    private lateinit var configurator: PrefConfigContract
    override val context: Context
        get() = configurator.context

    fun init(configurator: PrefConfigContract) {
        this.configurator = configurator
    }
}