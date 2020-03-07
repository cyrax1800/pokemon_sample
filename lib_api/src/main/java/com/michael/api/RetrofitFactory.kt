package com.michael.api

import com.michael.api.adapters.SimpleCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private var networkInterceptor: Interceptor? = null

    private val m_retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(defaultClient())
            .addCallAdapterFactory(SimpleCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val m_okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(defaultInterceptor)
        networkInterceptor?.also {
            builder.addNetworkInterceptor(it)
        }
        builder.build()
    }

    private val defaultInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        chain.proceed(requestBuilder.build())
    }

    fun defaultRetrofit(): Retrofit = m_retrofit
    fun defaultClient(): OkHttpClient = m_okHttpClient
    internal fun setNetworkInterceptor(interceptor: Interceptor) {
        networkInterceptor = interceptor
    }
}