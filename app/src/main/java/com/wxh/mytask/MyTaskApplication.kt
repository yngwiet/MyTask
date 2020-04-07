package com.wxh.mytask

import android.app.Application
import timber.log.Timber

class MyTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}