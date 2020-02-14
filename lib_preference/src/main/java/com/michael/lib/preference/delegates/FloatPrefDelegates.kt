package com.michael.lib.preference.delegates

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FloatPrefDelegates(
    private val pref: SharedPreferences,
    private val defaultValue: Float,
    private val commit: Boolean
) : ReadWriteProperty<Any, Float> {

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        pref.getFloat(property.name, defaultValue)

    @SuppressLint("ApplySharedPref")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
        val editor = pref.edit().putFloat(property.name, value)
        if (commit) editor.commit()
        else editor.apply()
    }
}