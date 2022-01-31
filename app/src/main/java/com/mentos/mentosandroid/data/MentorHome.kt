package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MentorHome(
    val mentos: Int,
    val MenteeCategory: ArrayList<MenteeCategory>,
    val otherMentee: ArrayList<Mentee>
) : Parcelable

@Parcelize
data class MenteeCategory(
    val menteeCategoryId: Int,
    val mentee: ArrayList<Mentee>
) : Parcelable

@Parcelize
data class Mentee(
    val menteeStudentId: Int,
    val nickName: String,
    val menteeMajor: String,
    val mentorYear: String,
    val menteeImage: String,
    val firstMajorCategory: Int,
    val secondMajorCategory: Int
) : Parcelable