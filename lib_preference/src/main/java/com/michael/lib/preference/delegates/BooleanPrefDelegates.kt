package com.michael.lib.preference.delegates

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BooleanPrefDelegates(
    private val pref: SharedPreferences,
    private val defaultValue: Boolean,
    private val commit: Boolean
) : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        pref.getBoolean(property.name, defaultValue)

    @SuppressLint("ApplySharedPref")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        val editor = pref.edit().putBoolean(property.name, value)
        if (commit) editor.commit()
        else editor.apply()
    }
}