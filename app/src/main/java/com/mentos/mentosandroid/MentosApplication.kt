package com.mentos.mentosandroid

import android.app.Application
import com.mentos.mentosandroid.data.local.SharedPreferenceController

class MentosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceController.init(this)
    }
}