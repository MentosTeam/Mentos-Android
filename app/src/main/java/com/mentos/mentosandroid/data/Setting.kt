package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Setting(
    val memberNickName: String,
    val memberStudentId: Int,
    val memberMajor: String,
    val memberMajorFirst: Int,
    val memberMajorSecond: Int,
    val memberImage: String?,
    val memberIntro: String
) : Parcelable
