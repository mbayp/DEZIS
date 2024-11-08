package com.dezis.geeks_dezis

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Dezis : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}