package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MentorProfile(
    val schoolName: String,
    val numOfMentoring: Int,
    val basicInformation: MentorBasicInformation,
    val posts: ArrayList<MyPost>,
    val reviews: ArrayList<Review>,
    val numOfMentos: ArrayList<NumOfMento>
) : Parcelable

@Parcelize
data class MentorBasicInformation(
    val memberId: Int,
    val name: String,
    val nickname: String,
    val studentId: Int, //학번
    val sex: String,
    val major: String,
    val profileImage: String,
    val mentoScore: Double,
    val schoolId: Int,
    val majorFirst: Int,
    val majorSecond: Int,
    val intro: String
) : Parcelable

@Parcelize
data class MyPost(
    val memberId: Int,
    val majorCategoryId: Int,
    val postId: Int,
    val postTitle: String,
    val postContents: String,
    val imageUrl: String
) : Parcelable

@Parcelize
data class Review(
    val reviewId: Int,
    val mentoringId: Int,
    val reviewScore: Double,
    val reviewText: String
) : Parcelable

@Parcelize
data class NumOfMento(
    val majorCategoryId: Int,
    val mentoringId: Int,
    val mentoringMentos: Int
) : Parcelable
