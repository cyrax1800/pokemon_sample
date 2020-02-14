package com.michael.lib.preference

import android.content.SharedPreferences
import org.junit.Test

import org.junit.Assert.*
import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object{
        @get:ClassRule
        @JvmStatic
        val prefRule = PrefRule()
    }

    @Test
    fun addition_isCorrect() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorrect1() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorrect2() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorrect3() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorrect4() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorrec5t() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorrec6t() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isCorr7ect() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }

    @Test
    fun addition_isC8orrect() {
        // Given current preference
        val userPreference = UserPref(prefRule.sharedPreferences)
        userPreference.balance = 111L
        val actions = Actions(userPreference)

        // Then
        actions.onCreate()
        assert(userPreference.balance == 123L)
    }
}

class Actions(val userPref: UserPref = UserPref(Pref.pref)) {
    fun onCreate() {
        userPref.balance = 123L
    }
}

class PrefRule: TestRule {
    var sharedPreferences: SharedPreferences = TestPreference()
    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement() {
            override fun evaluate() {
                sharedPreferences.edit().clear()
                base?.evaluate()
            }
        }
    }
}
