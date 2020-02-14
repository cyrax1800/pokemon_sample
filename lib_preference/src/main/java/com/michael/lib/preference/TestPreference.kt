package com.michael.lib.preference

import android.content.SharedPreferences

class TestPreference : SharedPreferences {

    private val preferenceMap = HashMap<String, Any>()
    private val preferenceEditor = TestPreferenceEditor(preferenceMap)

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return preferenceMap[key] as? Boolean ?: defValue
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return preferenceMap[key] as? Int ?: defValue
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return preferenceMap[key] as? Long ?: defValue
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return preferenceMap[key] as? Float ?: defValue
    }

    override fun getString(key: String?, defValue: String?): String? {
        return preferenceMap[key] as? String ?: defValue
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return preferenceMap[key] as? MutableSet<String> ?: defValues
    }

    override fun getAll(): MutableMap<String, *> = preferenceMap

    override fun edit(): SharedPreferences.Editor = preferenceEditor

    override fun contains(key: String?): Boolean = preferenceMap.contains(key)

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

    class TestPreferenceEditor(private val preferenceMap: HashMap<String, Any>) :
        SharedPreferences.Editor {

        override fun clear(): SharedPreferences.Editor {
            preferenceMap.clear()
            return this
        }

        override fun putLong(key: String, value: Long): SharedPreferences.Editor {
            preferenceMap[key] = value
            return this
        }

        override fun putInt(key: String, value: Int): SharedPreferences.Editor {
            preferenceMap[key] = value
            return this
        }

        override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
            preferenceMap[key] = value
            return this
        }

        override fun putString(key: String, value: String?): SharedPreferences.Editor {
            preferenceMap[key] = value.orEmpty()
            return this
        }

        override fun putStringSet(
            key: String,
            values: MutableSet<String>?
        ): SharedPreferences.Editor {
            preferenceMap[key] = values.orEmpty()
            return this
        }

        override fun putFloat(key: String, value: Float): SharedPreferences.Editor {
            preferenceMap[key] = value
            return this
        }

        override fun remove(key: String): SharedPreferences.Editor {
            preferenceMap.remove(key)
            return this
        }

        override fun commit(): Boolean {
            return true
        }

        override fun apply() {}
    }
}