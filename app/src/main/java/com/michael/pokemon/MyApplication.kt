package com.michael.pokemon

import android.app.Application
import androidx.multidex.MultiDex
import com.michael.api.Api

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Api.initializeHttpLogger(this)
        MultiDex.install(this)
    }
}