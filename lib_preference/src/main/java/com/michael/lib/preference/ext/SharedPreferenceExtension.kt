package com.michael.lib.preference.ext

import android.content.SharedPreferences
import com.michael.lib.preference.delegates.LongPrefDelegates
import com.michael.lib.preference.delegates.StringPrefDelegates
import kotlin.properties.ReadWriteProperty

fun SharedPreferences.string(
    defaultValue: String = "",
    commit: Boolean = false
): ReadWriteProperty<Any, String> = StringPrefDelegates(this, defaultValue, commit)

fun SharedPreferences.long(
    defaultValue: Long = 0L,
    commit: Boolean = false
): ReadWriteProperty<Any, Long> = LongPrefDelegates(this, defaultValue, commit)