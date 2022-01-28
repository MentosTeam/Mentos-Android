package com.mentos.mentosandroid.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPreferenceController {
    private const val NOW_STATE = "NOW_STATE"

    fun getNowState(context: Context): Int {
        val sdf = context.getSharedPreferences(NOW_STATE, MODE_PRIVATE)
        return sdf!!.getInt(NOW_STATE, 0)
    }

    fun setNowState(context: Context, state: Int?) {
        val sdf = context.getSharedPreferences(NOW_STATE, MODE_PRIVATE)
        val editor = sdf.edit()
        editor.putInt(NOW_STATE, state!!)
        editor.apply()
    }

    fun clearNowState(context: Context) {
        val sdf = context.getSharedPreferences(NOW_STATE, MODE_PRIVATE)
        sdf.edit().clear().apply()
    }


}