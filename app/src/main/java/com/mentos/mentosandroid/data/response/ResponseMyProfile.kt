package com.mentos.mentosandroid.data.response

data class ResponseMyProfile(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: MyProfileResult
)

data class MyProfileResult(
    val menteeProfile: MenteeProfile,
    val mentorProfile: MentorProfile
)

data class MenteeProfile(
    val basicInformation: MenteeBasicInformation,
    val numOfMentoring: Int,
    val schoolName: String
)

data class MentorProfile(
    val basicInformation: MentorBasicInformation,
    val numOfMentoring: Int,
    val numOfMentos: ArrayList<NumOfMentos>,
    val posts: ArrayList<MyPost>,
    val reviews: ArrayList<Review>,
    val schoolName: String
)

data class MenteeBasicInformation(
    val intro: String,
    val major: String,
    val majorFirst: Int,
    val majorSecond: Int,
    val memberId: Int,
    val name: String,
    val nickname: String,
    val profileImage: String?,
    val schoolId: Int,
    val sex: String,
    val studentId: Int
)

data class MentorBasicInformation(
    val intro: String,
    val major: String,
    val majorFirst: Int,
    val majorSecond: Int,
    val memberId: Int,
    val mentoScore: Double,
    val name: String,
    val nickname: String,
    val profileImage: String,
    val schoolId: Int,
    val sex: String,
    val studentId: Int
)

data class MyPost(
    val imageUrl: String?,
    val majorCategoryId: Int,
    val memberId: Int,
    val postContents: String,
    val postId: Int,
    val postTitle: String
)

data class NumOfMentos(
    val majorCategoryId: Int,
    val mentoringId: Int,
    val mentoringMentos: Int
)

data class Review(
    val reviewId: Int,
    val mentoringId: Int,
    val reviewScore: Double,
    val reviewText: String
)