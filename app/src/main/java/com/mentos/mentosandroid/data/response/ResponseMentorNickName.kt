package com.mentos.mentosandroid.data.response

data class ResponseMentorNicName(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: NickNameResult
)

data class NickNameResult(
    val mentiNickname: String,
    val mentoNickname: String
)