package com.michael.lib.preference

import android.content.Context
import android.content.SharedPreferences
import com.michael.lib.preference.ext.long
import com.michael.lib.preference.ext.string

object Pref {
    val pref = PrefConfigImpl.context.getSharedPreferences(
        "module",
        Context.MODE_PRIVATE
    )
}

interface PrefConfig {
    val context: Context
}

object PrefConfigImpl: PrefConfig {
    lateinit var configurator: PrefConfig
    override val context: Context
        get() = configurator.context

}

class UserPref(preference: SharedPreferences) {
    var userId by preference.string()
    var balance by preference.long()
}