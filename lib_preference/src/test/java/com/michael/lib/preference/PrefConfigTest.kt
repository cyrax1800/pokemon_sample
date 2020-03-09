package com.michael.lib.preference

import android.content.Context
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class PrefConfigTest {

    val mockContext = mockk<Context>(relaxed = true)

    @Test
    fun `given configurator, when getContext, should return assigned context`() {
        // given when
        PrefConfig.init(object : PrefConfigContract {
            override val context: Context
                get() = mockContext
        })

        // then
        Assert.assertEquals(PrefConfig.context, mockContext)
    }
}