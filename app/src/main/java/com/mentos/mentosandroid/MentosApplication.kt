package com.mentos.mentosandroid

import android.app.Application
import com.mentos.mentosandroid.util.SharedPreferenceController

class MentosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceController.init(this)
    }
}