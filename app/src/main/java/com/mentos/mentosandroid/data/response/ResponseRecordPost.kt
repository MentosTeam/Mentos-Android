package com.mentos.mentosandroid.data.response

data class ResponseRecordPost(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Int
)