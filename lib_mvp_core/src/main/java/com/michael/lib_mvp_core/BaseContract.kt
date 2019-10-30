package com.michael.lib_mvp_core

import androidx.lifecycle.Lifecycle

interface BaseContract {
    interface View

    interface Presenter<in T: View> {
        fun attach(view: T, viewLifecycle: Lifecycle)
    }
}