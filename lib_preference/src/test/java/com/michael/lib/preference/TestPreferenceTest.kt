package com.michael.lib.preference

import com.michael.lib.preference.test.TestPreference
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestPreferenceTest {
    private lateinit var testPreference: TestPreference

    @Before
    fun setup() {
        testPreference = TestPreference()
        testPreference.registerOnSharedPreferenceChangeListener { _, _ -> }

        testPreference.unregisterOnSharedPreferenceChangeListener { _, _ -> }
    }

    @Test
    fun `given a false preference, when get Boolean should return same value`() {
        // given
        testPreference.edit().putBoolean("test_boolean", false)

        // when
        val result = testPreference.getBoolean("test_boolean", false)

        // then
        Assert.assertEquals(false, result)
    }

    @Test
    fun `given a true preference, when get Boolean should return same value`() {
        // given
        testPreference.edit().putBoolean("test_boolean", true)

        // when
        val result = testPreference.getBoolean("test_boolean", false)

        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `given an empty preference, when get Boolean should return default value false`() {
        // when
        val result = testPreference.getBoolean("test_boolean", false)

        // then
        Assert.assertEquals(false, result)
    }

    @Test
    fun `given an empty preference, when get Boolean should return default value true`() {
        // when
        val result = testPreference.getBoolean("test_boolean", true)

        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `given a wrong type preference boolean, when getBoolean, should return default value`() {
        // given
        testPreference.edit().putString("test_boolean", "false")

        // when
        val result = testPreference.getBoolean("test_boolean", true)

        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `given an int preference, when getInt should return same value`() {
        // given
        testPreference.edit().putInt("test_int", 123)

        // when
        val result = testPreference.getInt("test_int", 0)

        Assert.assertEquals(123, result)
    }

    @Test
    fun `given an empty int preference, when getInt should return default value`() {
        // when
        val result = testPreference.getInt("test_int", 1234)

        Assert.assertEquals(1234, result)
    }

    @Test
    fun `given a wront type int preference, when getInt should return default value`() {
        // given
        testPreference.edit().putString("test_int", "123")

        // when
        val result = testPreference.getInt("test_int", 0)

        Assert.assertEquals(0, result)
    }

    @Test
    fun `given an long preference, when getLong should return same value`() {
        // given
        testPreference.edit().putLong("test_long", 123L)

        // when
        val result = testPreference.getLong("test_long", 0L)

        Assert.assertEquals(123L, result)
    }

    @Test
    fun `given an empty long preference, when getLong should return default value`() {
        // when
        val result = testPreference.getLong("test_long", 1234L)

        Assert.assertEquals(1234L, result)
    }

    @Test
    fun `given a wrong type long preference, when getLong should return default value`() {
        // given
        testPreference.edit().putString("test_long", "123L")

        // when
        val result = testPreference.getLong("test_long", 0L)

        Assert.assertEquals(0L, result)
    }

    @Test
    fun `given an float preference, when getFloat should return same value`() {
        // given
        testPreference.edit().putFloat("test_float", 123f)

        // when
        val result = testPreference.getFloat("test_float", 0f)

        Assert.assertEquals(123f, result)
    }

    @Test
    fun `given an empty float preference, when getFloat should return default value`() {
        // when
        val result = testPreference.getFloat("test_float", 1234f)

        Assert.assertEquals(1234f, result)
    }

    @Test
    fun `given a wrong type float preference, when getFloat should return default value`() {
        // given
        testPreference.edit().putString("test_float", "123f")

        // when
        val result = testPreference.getFloat("test_float", 0f)

        Assert.assertEquals(0f, result)
    }

    @Test
    fun `given a string preference, when getString, should return same value`() {
        // given
        testPreference.edit().putString("test_string", "string_value")

        // when
        val result = testPreference.getString("test_string", "default_value")

        // then
        Assert.assertEquals("string_value", result)
    }

    @Test
    fun `given an empty string preference, when getString, should return default value`() {
        // when
        val result = testPreference.getString("test_string", "default_value")

        // then
        Assert.assertEquals("default_value", result)
    }

    @Test
    fun `given a wrong type string preference, when getString, should return default value`() {
        // given
        testPreference.edit().putInt("test_string", 123)

        // when
        val result = testPreference.getString("test_string", "default_value")

        // then
        Assert.assertEquals("default_value", result)
    }

    @Test
    fun `given a null type string preference, when getString, should return empty string`() {
        // given
        testPreference.edit().putString("test_string", null)

        // when
        val result = testPreference.getString("test_string", "default_value")

        // then
        assert(result!!.isEmpty())
        Assert.assertEquals("", result)
    }

    @Test
    fun `given a null default string preference, when getString, should return null`() {
        // when
        val result = testPreference.getString("test_string", null)

        // then
        Assert.assertEquals(null, result)
    }

    @Test
    fun `given a stringSet preference, when getStringSet, shoul return same value`() {
        // given
        val assertSet = mutableSetOf("asd", "def")
        testPreference.edit().putStringSet("test_stringset", assertSet)

        // when
        val result = testPreference.getStringSet("test_stringset", mutableSetOf())

        // then
        Assert.assertEquals(2, result!!.size)
        Assert.assertEquals(assertSet, result)
    }

    @Test
    fun `given an empty stringSet preference, when getStringSet, should return default value`() {
        // when
        val result = testPreference.getStringSet("test_stringset", mutableSetOf())

        // then
        Assert.assertEquals(0, result!!.size)
    }

    @Test
    fun `given a wrong type stringSet preference, when getStringSet, should return default value`() {
        // given
        testPreference.edit().putInt("test_stringset", 123)

        // when
        val result = testPreference.getStringSet("test_stringset", mutableSetOf())

        // then
        Assert.assertEquals(0, result!!.size)
    }

    @Test
    fun `given a null default stringSet preference, when getStringSet, should return null`() {
        // when
        val result = testPreference.getStringSet("test_stringset", null)

        // then
        Assert.assertEquals(null, result)
    }

    @Test
    fun `given a null type stringSet preference, when getStringSet, should return empty stringSet`() {
        // given
        testPreference.edit().putStringSet("test_stringset", null)

        // when
        val result = testPreference.getStringSet("test_stringset", mutableSetOf())

        // then
        assert(result!!.isEmpty())
    }

    @Test
    fun `given a preference, when getAll, should return all value`() {
        // given
        testPreference.edit().putInt("test_int", 123)
        testPreference.edit().putString("test_string", "asdf")

        // when
        val result = testPreference.all

        // then
        Assert.assertEquals(2, result.size)
        Assert.assertEquals(123, result["test_int"])
        Assert.assertEquals("asdf", result["test_string"])
    }

    @Test
    fun `given a preference, when contains, should return true if contain`() {
        // given
        testPreference.edit().putInt("test_int", 123)
        testPreference.edit().putString("test_string", "asdf")

        // when
        val result = testPreference.contains("test_int")

        // then
        Assert.assertEquals(true, result)
    }

    @Test
    fun `given a preference, when contains, should return false if not contain`() {
        // given
        testPreference.edit().putInt("test_int", 123)
        testPreference.edit().putString("test_string", "asdf")

        // when
        val result = testPreference.contains("test_in2t")

        // then
        Assert.assertEquals(false, result)
    }

    @Test
    fun `given a preference, when clear, should empty the preference`() {
        // given
        testPreference.edit().putInt("test_int", 123)
        testPreference.edit().putString("test_string", "asdf")

        // when
        testPreference.edit().clear().apply()
        val result = testPreference.all

        // then
        Assert.assertEquals(0, result.size)
    }

    @Test
    fun `given a preference, when remove key, should remove the key on preference`() {
        // given
        testPreference.edit().putInt("test_int", 123)
        testPreference.edit().putString("test_string", "asdf")

        // when
        testPreference.edit().remove("test_int").commit()
        val result = testPreference.getInt("test_int", 1234)

        // then
        Assert.assertEquals(1234, result)
    }
}