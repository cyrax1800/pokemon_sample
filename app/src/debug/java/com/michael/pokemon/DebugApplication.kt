package com.michael.pokemon

import com.michael.api.DebugApi

class DebugApplication: MyApplication() {

    override fun onCreate() {
        super.onCreate()
        DebugApi.initializeHttpLogger(this)
    }
}