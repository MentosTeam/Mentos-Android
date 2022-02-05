package com.mentos.mentosandroid.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPreferenceController {
    private const val NOW_STATE = "NOW_STATE"
    private const val MY_MENTOS = "MY_MENTOS"
    private const val OPEN_SEX = "OPEN_SEX"

    //멘토 <-> 멘티
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

    //현재 보유 멘토스
    fun getMyMentos(context: Context): Int {
        val sdf = context.getSharedPreferences(MY_MENTOS, MODE_PRIVATE)
        return sdf!!.getInt(MY_MENTOS, 0)
    }

    fun setMyMentos(context: Context, state: Int?) {
        val sdf = context.getSharedPreferences(MY_MENTOS, MODE_PRIVATE)
        val editor = sdf.edit()
        editor.putInt(MY_MENTOS, state!!)
        editor.apply()
    }

    fun clearMyMentos(context: Context) {
        val sdf = context.getSharedPreferences(MY_MENTOS, MODE_PRIVATE)
        sdf.edit().clear().apply()
    }

    //성별 공개여부
    fun getOpenSex(context: Context): Boolean {
        val sdf = context.getSharedPreferences(OPEN_SEX, MODE_PRIVATE)
        return sdf!!.getBoolean(OPEN_SEX, true)
    }

    fun setOpenSex(context: Context, state: Boolean?) {
        val sdf = context.getSharedPreferences(OPEN_SEX, MODE_PRIVATE)
        val editor = sdf.edit()
        editor.putBoolean(OPEN_SEX, state!!)
        editor.apply()
    }

    fun clearOpenSex(context: Context) {
        val sdf = context.getSharedPreferences(OPEN_SEX, MODE_PRIVATE)
        sdf.edit().clear().apply()
    }

}