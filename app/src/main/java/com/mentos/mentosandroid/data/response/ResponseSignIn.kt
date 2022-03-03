package com.mentos.mentosandroid.data.response

data class ResponseSignIn(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: SignIn
)

data class SignIn(
    val memberId: Int,
    val jwt: String,
    val mentor: Int,
    val mentee: Int,
    val genderFlag: Int,
    val mentorNotificationFlag: Int
)
