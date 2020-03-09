package com.michael.lib.preference

import android.content.SharedPreferences
import com.michael.lib.preference.ext.*
import com.michael.lib.preference.test.TestPreference
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test not covered is getString and getStringSet from return null,
 * this is can't reach because already intended as non null type
 */

class PreferenceTest {

    private class TestPref(sharedPreferences: SharedPreferences) {
        var intPref by sharedPreferences.int()
        var longPref by sharedPreferences.long()
        var floatPref by sharedPreferences.float()
        var booleanPref by sharedPreferences.boolean()
        var stringPref by sharedPreferences.string()
        var stringSetPref by sharedPreferences.stringSet()

        var intCommitPref by sharedPreferences.int(defaultValue = 123, commit = true)
        var longCommitPref by sharedPreferences.long(defaultValue = 123L, commit = true)
        var floatCommitPref by sharedPreferences.float(defaultValue = 123F, commit = true)
        var booleanCommitPref by sharedPreferences.boolean(defaultValue = true, commit = true)
        var stringCommitPref by sharedPreferences.string(defaultValue = "123", commit = true)
        var stringSetCommitPref by sharedPreferences.stringSet(
            defaultValue = mutableSetOf(
                "test", "test2"
            ), commit = true
        )
    }

    private lateinit var testPreferenceTest: TestPreference
    private lateinit var testPref: TestPref

    @Before
    fun setup() {
        testPreferenceTest = TestPreference()
        testPref = TestPref(testPreferenceTest)
    }

    @Test
    fun `given a preference, when getIntPref, should return preference value`() {
        // given
        testPreferenceTest.edit().putInt("intPref", 123)

        // when
        val result = testPref.intPref

        // then
        Assert.assertEquals(123, result)
    }

    @Test
    fun `given a preference, when setIntPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putInt("intPref", 123)

        // when
        testPref.intPref = 1234
        val result = testPref.intPref

        // then
        Assert.assertEquals(1234, result)
    }

    @Test
    fun `given a preference, when intCommitPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putInt("intCommitPref", 123)

        // when
        testPref.intCommitPref = 1234
        val result = testPref.intCommitPref

        // then
        Assert.assertEquals(1234, result)
    }

    @Test
    fun `given a preference, when getLongPref, should return preference value`() {
        // given
        testPreferenceTest.edit().putLong("longPref", 123L)

        // when
        val result = testPref.longPref

        // then
        Assert.assertEquals(123L, result)
    }

    @Test
    fun `given a preference, when getLongPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putLong("longPref", 123L)

        // when
        testPref.longPref = 1234L
        val result = testPref.longPref

        // then
        Assert.assertEquals(1234L, result)
    }

    @Test
    fun `given a preference, when longCommitPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putLong("longCommitPref", 123L)

        // when
        testPref.longCommitPref = 1234L
        val result = testPref.longCommitPref

        // then
        Assert.assertEquals(1234L, result)
    }

    @Test
    fun `given a preference, when getFloatPref, should return preference value`() {
        // given
        testPreferenceTest.edit().putFloat("floatPref", 123F)

        // when
        val result = testPref.floatPref

        // then
        Assert.assertEquals(123F, result)
    }

    @Test
    fun `given a preference, when getFloatPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putFloat("floatPref", 123F)

        // when
        testPref.floatPref = 1234F
        val result = testPref.floatPref

        // then
        Assert.assertEquals(1234F, result)
    }

    @Test
    fun `given a preference, when floatCommitPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putFloat("floatCommitPref", 123F)

        // when
        testPref.floatCommitPref = 1234F
        val result = testPref.floatCommitPref

        // then
        Assert.assertEquals(1234F, result)
    }

    @Test
    fun `given a preference, when getBooleanPref, should return preference value`() {
        // given
        testPreferenceTest.edit().putBoolean("booleanPref", false)

        // when
        val result = testPref.booleanPref

        // then
        Assert.assertEquals(false, result)
    }

    @Test
    fun `given a preference, when getBooleanPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putBoolean("booleanPref", true)

        // when
        testPref.booleanPref = false
        val result = testPref.booleanPref

        // then
        Assert.assertEquals(false, result)
    }

    @Test
    fun `given a preference, when booleanCommitPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putBoolean("booleanCommitPref", false)

        // when
        testPref.booleanCommitPref = true
        val result = testPref.booleanCommitPref

        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `given a preference, when getStringPref, should return preference value`() {
        // given
        testPreferenceTest.edit().putString("stringPref", "asdf")

        // when
        val result = testPref.stringPref

        // then
        Assert.assertEquals("asdf", result)
    }

    @Test
    fun `given a preference, when getStringPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putString("stringPref", "asdf")

        // when
        testPref.stringPref = "qwerty"
        val result = testPref.stringPref

        // then
        Assert.assertEquals("qwerty", result)
    }

    @Test
    fun `given a preference, when stringCommitPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putString("stringCommitPref", "asdf")

        // when
        testPref.stringCommitPref = "qwerty"
        val result = testPref.stringCommitPref

        // then
        Assert.assertEquals("qwerty", result)
    }

    @Test
    fun `given a preference, when getStringSetPref, should return preference value`() {
        // given
        testPreferenceTest.edit().putStringSet("stringSetPref", mutableSetOf("real1"))

        // when
        val result = testPref.stringSetPref

        // then
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("real1", result.first())
    }

    @Test
    fun `given a preference, when getStringSetPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putStringSet("stringSetPref", mutableSetOf("real1"))

        // when
        testPref.stringSetPref = mutableSetOf("real2", "real3")
        val result = testPref.stringSetPref

        // then
        Assert.assertEquals(2, result.size)
        Assert.assertEquals("real2", result.first())
    }

    @Test
    fun `given a preference, when stringSetCommitPref, should set preference value`() {
        // given
        testPreferenceTest.edit().putStringSet("stringSetCommitPref", mutableSetOf("real1"))

        // when
        testPref.stringSetCommitPref = mutableSetOf("real2", "real3")
        val result = testPref.stringSetCommitPref

        // then
        Assert.assertEquals(2, result.size)
        Assert.assertEquals("real2", result.first())
    }

    @Test
    fun `given an preference, when call pref, should return all default value`() {
        // when
        val intResult = testPref.intCommitPref
        val longResult = testPref.longCommitPref
        val floatResult = testPref.floatCommitPref
        val booleanResult = testPref.booleanCommitPref
        val stringResult = testPref.stringCommitPref
        val stringSetResult = testPref.stringSetCommitPref

        // then
        Assert.assertEquals(123, intResult)
        Assert.assertEquals(123L, longResult)
        Assert.assertEquals(123F, floatResult)
        Assert.assertEquals(true, booleanResult)
        Assert.assertEquals("123", stringResult)
        Assert.assertEquals(2, stringSetResult.size)
        Assert.assertEquals("test", stringSetResult.first())
    }
}