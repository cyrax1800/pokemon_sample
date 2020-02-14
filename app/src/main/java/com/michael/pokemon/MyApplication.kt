package com.michael.pokemon

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.michael.api.Api
import com.michael.lib.preference.PrefConfig
import com.michael.lib.preference.PrefConfigImpl

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Api.initializeHttpLogger(this)
        PrefConfigImpl.configurator = object : PrefConfig {
            override val context: Context
                get() = this@MyApplication
        }
        MultiDex.install(this)
    }
}