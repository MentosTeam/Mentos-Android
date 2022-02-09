package com.mentos.mentosandroid.data.response

data class ResponseSetting(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SettingResult
)

data class SettingResult(
    val memberImage: String?,
    val memberIntro: String,
    val memberMajor: String,
    val memberMajorFirst: Int,
    val memberMajorSecond: Int,
    val memberNickName: String,
    val memberStudentId: Int
)