package com.michael.pokemon

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.michael.lib.preference.PrefConfig
import com.michael.lib.preference.PrefConfigContract

open class MyApplication: Application(), PrefConfigContract {

    override fun onCreate() {
        super.onCreate()

        PrefConfig.init(this)
        MultiDex.install(this)
    }

    override val context: Context
        get() = this
}