package com.lyhv.mvvm.ui

import android.app.Application
import com.lyhv.mvvm.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Enable Debugging for Kotlin Coroutines
        System.setProperty("kotlinx.coroutines.debug", if (BuildConfig.DEBUG) "on" else "off")
    }
}
