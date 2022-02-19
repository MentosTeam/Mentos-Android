package com.mentos.mentosandroid.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferenceController {
    private const val STORAGE_KEY = "user_auth"
    private const val NOW_STATE = "NOW_STATE"
    private const val MY_MENTOS = "MY_MENTOS"
    private const val OPEN_SEX = "OPEN_SEX"
    private const val JWT_TOKEN = "JWT_TOKEN"
    private const val USER_EMAIL = "USER_EMAIL"
    private const val USER_PW = "USER_PW"
    private const val DEVICE_FCM_TOKEN = "DEVICE_FCM_TOKEN"
    private const val MENTOR_AGREEMENT_PUSH = "MENTOR_AGREEMENT_PUSH"
    private const val MENTEE_AGREEMENT_PUSH = "MENTEE_AGREEMENT_PUSH"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            MODE_PRIVATE
        )
    }

    //멘토 <-> 멘티
    fun getNowState(): Int {
        return sharedPreferences.getInt(NOW_STATE, -1)
    }

    fun setNowState(state: Int) {
        sharedPreferences.edit()
            .putInt(NOW_STATE, state)
            .apply()
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

    // jwt 토큰
    fun getJwtToken(): String {
        return sharedPreferences.getString(JWT_TOKEN, "") ?: ""
    }

    fun setJwtToken(jwt: String) {
        sharedPreferences.edit()
            .putString(JWT_TOKEN, jwt)
            .apply()
    }

    fun clearAuthData() {
        sharedPreferences.edit().clear().apply()
    }

    // AutoLogin
    fun setAutoLogin(email: String, pw: String) {
        sharedPreferences.edit()
            .putString(USER_EMAIL, email)
            .putString(USER_PW, pw)
            .apply()
    }

    fun getUserPw(): String {
        return sharedPreferences.getString(USER_PW, "") ?: ""
    }

    fun getUserEmail(): String {
        return sharedPreferences.getString(USER_EMAIL, "") ?: ""
    }

    // device fcm 토큰
    fun getDeviceFcmToken(): String {
        return sharedPreferences.getString(DEVICE_FCM_TOKEN, "") ?: ""
    }

    fun setDeviceFcmToken(token: String) {
        sharedPreferences.edit()
            .putString(DEVICE_FCM_TOKEN, token)
            .apply()
    }

    // 푸시알림 설정여부
    fun getAgreementPush(who: Int): Boolean {
        return when (who) {
            0 -> {
                sharedPreferences.getBoolean(MENTOR_AGREEMENT_PUSH, true)
            }
            else -> {
                sharedPreferences.getBoolean(MENTEE_AGREEMENT_PUSH, true)
            }
        }
    }

    fun setAgreementPush(who: Int, isAgree: Boolean) {
        when (who) {
            0 -> {
                sharedPreferences.edit()
                    .putBoolean(MENTOR_AGREEMENT_PUSH, isAgree)
                    .apply()
            }
            else -> {
                sharedPreferences.edit()
                    .putBoolean(MENTEE_AGREEMENT_PUSH, isAgree)
                    .apply()
            }
        }
    }
}