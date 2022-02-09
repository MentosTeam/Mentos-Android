package com.mentos.mentosandroid.data.response

data class BaseResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)
