package com.mentos.mentosandroid.data.response

data class ResponseHomeMentee(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: HomeMenteeResult
)

data class HomeMenteeResult(
    val mentorCategory: ArrayList<MentorCategory>,
    val mentos: Int,
    val otherMentor: ArrayList<OtherMentor>
)

data class MentorCategory(
    val mentorCategoryId: Int,
    val mentorPost: ArrayList<MentorPost>
)

data class MentorPost(
    val mentorImage: String?,
    val mentorMajor: String,
    val mentorStudentId: Int,
    val nickName: String,
    val postCategoryId: Int,
    val postContents: String,
    val postId: Int,
    val postImgUrl: String?,
    val postTitle: String
)

data class OtherMentor(
    val mentorImage: String?,
    val mentorMajor: String,
    val mentorStudentId: Int,
    val nickName: String,
    val mentorYear: String,
    val firstMajorCategory: Int,
    val secondMajorCategory: Int
)