package com.srappetito.flybonditestmvvm

import android.support.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationMain : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}