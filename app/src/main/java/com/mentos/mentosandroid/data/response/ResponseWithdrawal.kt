package com.mentos.mentosandroid.data.response

data class ResponseWithdrawal(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)