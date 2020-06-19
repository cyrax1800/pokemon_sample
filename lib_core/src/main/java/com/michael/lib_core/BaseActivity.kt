package com.michael.lib_core

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.android.asCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

internal val mainCompat = Handler(Looper.getMainLooper()).asCoroutineDispatcher("BaseActivity")

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    private val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = mainCompat + supervisorJob

    abstract val contentView: Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        initInjector()
        initView(savedInstanceState)
    }
}