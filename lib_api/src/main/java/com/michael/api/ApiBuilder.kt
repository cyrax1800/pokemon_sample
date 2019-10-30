package com.michael.api

import retrofit2.Retrofit
import java.util.HashMap
import kotlin.reflect.KClass

internal class ApiBuilder {

    private var retrofit: Retrofit = RetrofitFactory.defaultRetrofit()
    private val serviceCache = HashMap<String, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T> getInternalCachedService(serviceClass: Class<T>): T {
        val key = serviceClass.canonicalName?: serviceClass.name

        return serviceCache.getOrPut(key) {
            return retrofit.create(serviceClass)
        } as T
    }

    fun <T : Any> service(kotlinClass: KClass<T>): T {
        return service(kotlinClass.java)
    }

    fun <T> service(serviceClass: Class<T>): T {
        return getInternalCachedService(serviceClass)
    }
}