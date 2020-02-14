package com.michael.lib.preference.delegates

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LongPrefDelegates(
    private val pref: SharedPreferences,
    private val defaultValue: Long,
    private val commit: Boolean
) : ReadWriteProperty<Any, Long> {

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        pref.getLong(property.name, defaultValue)

    @SuppressLint("ApplySharedPref")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        val editor = pref.edit().putLong(property.name, value)
        if (commit) editor.commit()
        else editor.apply()
    }
}