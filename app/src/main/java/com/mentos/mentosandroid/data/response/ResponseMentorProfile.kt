package com.mentos.mentosandroid.data.response

data class ResponseMentorProfile(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: MentorProfile
)

