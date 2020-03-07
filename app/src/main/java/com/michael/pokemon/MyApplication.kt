package com.michael.pokemon

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.michael.api.Api
import com.michael.lib.preference.PrefConfig
import com.michael.lib.preference.PrefConfigContract

class MyApplication: Application(), PrefConfigContract {

    override fun onCreate() {
        super.onCreate()
        Api.initializeHttpLogger(this)

        PrefConfig.init(this)
        MultiDex.install(this)
    }

    override val context: Context
        get() = this
}