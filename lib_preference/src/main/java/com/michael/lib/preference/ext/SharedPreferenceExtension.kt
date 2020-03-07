package com.michael.lib.preference.ext

import android.content.SharedPreferences
import com.michael.lib.preference.delegates.*
import kotlin.properties.ReadWriteProperty

fun SharedPreferences.string(
    defaultValue: String = "",
    commit: Boolean = false
): ReadWriteProperty<Any, String> = StringPrefDelegates(this, defaultValue, commit)

fun SharedPreferences.long(
    defaultValue: Long = 0L,
    commit: Boolean = false
): ReadWriteProperty<Any, Long> = LongPrefDelegates(this, defaultValue, commit)

fun SharedPreferences.int(
    defaultValue: Int = 0,
    commit: Boolean = false
): ReadWriteProperty<Any, Int> = IntPrefDelegates(this, defaultValue, commit)

fun SharedPreferences.boolean(
    defaultValue: Boolean = false,
    commit: Boolean = false
): ReadWriteProperty<Any, Boolean> = BooleanPrefDelegates(this, defaultValue, commit)

fun SharedPreferences.float(
    defaultValue: Float = 0F,
    commit: Boolean = false
): ReadWriteProperty<Any, Float> = FloatPrefDelegates(this, defaultValue, commit)

fun SharedPreferences.stringSet(
    defaultValue: MutableSet<String> = mutableSetOf(),
    commit: Boolean = false
): ReadWriteProperty<Any, MutableSet<String>> {
    return StringSetPrefDelegates(this, defaultValue, commit)
}