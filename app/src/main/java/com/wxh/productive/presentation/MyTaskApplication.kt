package com.wxh.productive.presentation

import android.app.Application
import timber.log.Timber

class MyTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}