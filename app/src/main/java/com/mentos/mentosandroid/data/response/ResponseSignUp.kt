package com.mentos.mentosandroid.data.response

data class ResponseSignUp(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: SignUp
)

data class SignUp(
    val memberId: Int,
    val jwt: String
)
