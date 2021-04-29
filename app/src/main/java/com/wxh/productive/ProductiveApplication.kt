package com.wxh.productive

import android.app.Application
import timber.log.Timber

class ProductiveApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}