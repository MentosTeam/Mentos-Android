package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenteeHome(
    val mentos: Int,
    val MentorCategory: ArrayList<MentorCategory>,
    val otherMentor: ArrayList<OtherMentor>
) : Parcelable

@Parcelize
data class MentorCategory(
    val mentorCategoryId: Int,
    val mentorPost: ArrayList<MentorPost>
) : Parcelable

@Parcelize
data class MentorPost(
    val mentorStudentId: Int,
    val nickName: String,
    val mentorMajor: String,
    val mentorImage: String,
    val postId: Int,
    val postCategoryId: Int,
    val postTitle: String,
    val postContents: String,
    val postImgUrl: String?
) : Parcelable

@Parcelize
data class OtherMentor(
    val mentorStudentId: Int,
    val nickName: String,
    val mentorMajor: String,
    val mentorYear: String,
    val mentorImage: String,
    val firstMajorCategory: Int,
    val secondMajorCategory: Int
) : Parcelable