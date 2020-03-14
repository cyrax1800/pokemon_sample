package com.michael.api

import kotlin.reflect.KClass

object Api {

    fun <T> service(serviceClass: Class<T>): T {
        return ApiBuilder().service(serviceClass)
    }

    fun <T : Any> service(kotlinClass: KClass<T>): T {
        return ApiBuilder().service(kotlinClass)
    }
}

