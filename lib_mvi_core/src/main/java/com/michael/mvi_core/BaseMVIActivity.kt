package com.michael.mvi_core

import com.michael.lib_core.BaseActivity

abstract class BaseMVIActivity<S : BaseState> : BaseActivity() {
    abstract fun render(state: S)
}