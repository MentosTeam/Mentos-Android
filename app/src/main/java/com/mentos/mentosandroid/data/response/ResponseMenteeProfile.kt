package com.mentos.mentosandroid.data.response

data class ResponseMenteeProfile(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: MenteeProfile
)

