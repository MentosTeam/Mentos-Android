package com.mentos.mentosandroid

import android.app.Application
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import timber.log.Timber

class MentosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceController.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}