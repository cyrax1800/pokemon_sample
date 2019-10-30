package com.michael.api

import android.app.Application
import com.facebook.stetho.Stetho
import kotlin.reflect.KClass

object Api {

    fun <T> service(serviceClass: Class<T>): T {
        return ApiBuilder().service(serviceClass)
    }

    fun <T: Any> service(kotlinClass: KClass<T>): T {
        return ApiBuilder().service(kotlinClass)
    }

    fun initializeHttpLogger(application: Application) {
        Stetho.initializeWithDefaults(application)
    }
}

