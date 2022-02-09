package com.mentos.mentosandroid.data.response

data class ResponseHomeMentor(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: HomeMentorResult
)

data class HomeMentorResult(
    val menteeCategory: ArrayList<MenteeCategory>,
    val mentos: Int,
    val otherMentee: ArrayList<Mentee>
)


data class MenteeCategory(
    val mentee: ArrayList<Mentee>,
    val menteeCategoryId: Int
)

data class Mentee(
    val firstMajorCategory: Int,
    val menteeImage: String?,
    val menteeMajor: String,
    val menteeStudentId: Int,
    val menteeYear: String,
    val nickName: String,
    val secondMajorCategory: Int
)
