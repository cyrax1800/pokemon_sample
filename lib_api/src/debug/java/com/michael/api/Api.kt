package com.michael.api

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

object DebugApi {

    fun initializeHttpLogger(application: Application) {
        SoLoader.init(application, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(application)) {
            val client = AndroidFlipperClient.getInstance(application)
            val networkFlipperPlugin = NetworkFlipperPlugin()
            client.addPlugin(networkFlipperPlugin)
            val databasesFlipperPlugin = DatabasesFlipperPlugin(application)
            client.addPlugin(databasesFlipperPlugin)
            val sharedPreferenceFlipperPlugin = SharedPreferencesFlipperPlugin(application)
            client.addPlugin(sharedPreferenceFlipperPlugin)
            client.addPlugin(LeakCanaryFlipperPlugin())
            client.addPlugin(CrashReporterPlugin.getInstance())
            client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
            RetrofitFactory.setNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
            client.start()
        }
    }
}

