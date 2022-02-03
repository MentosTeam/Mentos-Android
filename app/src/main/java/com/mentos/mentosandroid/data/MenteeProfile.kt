package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenteeProfile(
    val schoolName: String,
    val numOfMentoring: Int,
    val basicInformation: MenteeBasicInformation
) : Parcelable

@Parcelize
data class MenteeBasicInformation(
    val memberId: Int,
    val name: String,
    val nickname: String,
    val studentId: Int, //학번
    val sex: String,
    val major: String,
    val profileImage: String,
    val schoolId: Int,
    val majorFirst: Int,
    val majorSecond: Int,
    val intro: String,
) : Parcelable