package com.michael.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.michael.api.adapters.SimpleCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val m_retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(defaultClient())
            .addCallAdapterFactory(SimpleCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val m_okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(defaultInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    private val defaultInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        chain.proceed(requestBuilder.build())
    }

    fun defaultRetrofit(): Retrofit = m_retrofit
    fun defaultClient(): OkHttpClient = m_okHttpClient
}