package com.mentos.mentosandroid.data.response

data class ResponseCreateProfile(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: CreateProfile
)

data class CreateProfile(
    val memberId: Int,
    val profile: String,
    val nickname: String,
    val profileImgUrl: String?
)